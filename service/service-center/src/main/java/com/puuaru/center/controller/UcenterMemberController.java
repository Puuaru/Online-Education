package com.puuaru.center.controller;


import com.puuaru.center.entity.UcenterMember;
import com.puuaru.center.service.UcenterMemberService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
@RestController
@RequestMapping("/center/ucenter-member")
public class UcenterMemberController {
    private final UcenterMemberService memberService;

    @Autowired
    public UcenterMemberController(UcenterMemberService ucenterMemberService) {
        this.memberService = ucenterMemberService;
    }

    /**
     * 前台登录
     * @param ucenterMember 前台前端传递的用户数据
     * @return 用户数据token
     */
    @PostMapping("/login")
    @ApiOperation("前台登录")
    public ResultCommon login(UcenterMember ucenterMember) {
        String token = memberService.login(ucenterMember);
        return ResultCommon.success().setData("token", token);
    }
}

