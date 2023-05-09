package com.puuaru.filter;

import com.puuaru.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: JwtAuthenticationFilter
 * @Author: puuaru
 * @Date: 2023/5/9
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate redisTemplate;

    public JwtAuthenticationFilter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authToken = getAuthenticationToken(request);
        if (authToken != null) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        // 递交给后续处理
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthenticationToken(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwt(request);
        if (userId == null) {
            return null;
        }
        List<String> permissions = (List<String>) redisTemplate.opsForValue().get(userId);
        if (permissions == null) {
            return null;
        }
        List<GrantedAuthority> authorities = permissions.stream()
                .filter(Objects::nonNull)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }
}
