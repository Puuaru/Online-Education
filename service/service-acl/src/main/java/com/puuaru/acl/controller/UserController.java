package com.puuaru.acl.controller;


import com.puuaru.acl.service.UserService;
import com.puuaru.entity.User;
import com.puuaru.utils.JwtUtils;
import com.puuaru.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/acl/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    /**
     * 暂时替代实际的用户查找
     * @return
     */
    @GetMapping("/info")
    public ResultCommon info(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwt(request);
        User user = userService.getById(userId);

        return ResultCommon.success()
                .setData("name", user.getNickName())
                .setData("avatar", user.getAvatar());
    }
}

