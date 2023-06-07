package com.puuaru.acl.controller;

import com.puuaru.acl.vo.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Description: LoginLogoutController
 * @Author: puuaru
 * @Date: 2023/5/6
 */
@RestController
@RequestMapping("/acl/index")
public class LoginLogoutController {

    /**
     * 获取当前用户的必要信息
     * 应从Spring Security的上下文中获取当前用户
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户的必要信息")
    public Collection getUserInfo() {
        // 因当时存入token的是userId
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

}
