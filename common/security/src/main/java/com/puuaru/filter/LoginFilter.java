package com.puuaru.filter;

import com.alibaba.fastjson.JSON;
import com.puuaru.entity.SecurityUser;
import com.puuaru.entity.User;
import com.puuaru.utils.JwtUtils;
import com.puuaru.utils.ResponseUtil;
import com.puuaru.utils.ResultCommon;
import lombok.SneakyThrows;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description: LoginFilter
 * @Author: puuaru
 * @Date: 2023/5/6
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisTemplate redisTemplate;
    private AuthenticationManager authenticationManager;

    public LoginFilter(RedisTemplate redisTemplate, AuthenticationManager authenticationManager) {
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        super.setPostOnly(false);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/acl/index/login"));
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = JSON.parseObject(request.getInputStream(), User.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取认证后的用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String token = JwtUtils.generateJwt(user.getUser().getUsername(), user.getUser().getNickName());
        redisTemplate.opsForValue().set(token, user.getPermissions());
        ResponseUtil.out(response, ResultCommon.success().setData("token", token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, ResultCommon.fail());
    }
}
