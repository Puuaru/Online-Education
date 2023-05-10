package com.puuaru.handler;

import com.puuaru.utils.JwtUtils;
import com.puuaru.utils.ResponseUtil;
import com.puuaru.utils.ResultCommon;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TokenLogoutHandler
 * @Author: puuaru
 * @Date: 2023/5/10
 */
public class TokenLogoutHandler implements LogoutHandler {

    private final RedisTemplate redisTemplate;

    public TokenLogoutHandler(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String userId = JwtUtils.getMemberIdByJwt(httpServletRequest);
        redisTemplate.delete(userId);
        ResponseUtil.out(httpServletResponse, ResultCommon.success().setMessage("Logout successfully"));
    }
}
