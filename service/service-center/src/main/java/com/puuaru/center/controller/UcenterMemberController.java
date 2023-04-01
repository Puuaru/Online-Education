package com.puuaru.center.controller;


import com.puuaru.servicebase.entity.UcenterMember;
import com.puuaru.center.service.UcenterMemberService;
import com.puuaru.center.vo.MemberRegisterVo;
import com.puuaru.utils.JwtUtils;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
@RestController
@RequestMapping("/center/member")
@CrossOrigin
public class UcenterMemberController {
    private final UcenterMemberService memberService;

    @Autowired
    public UcenterMemberController(UcenterMemberService ucenterMemberService) {
        this.memberService = ucenterMemberService;
    }

    /**
     * 前台登录
     *
     * @param ucenterMember 前台前端传递的用户数据
     * @return 用户数据token
     */
    @PostMapping("/login")
    @ApiOperation("前台登录")
    public ResultCommon login(@RequestBody UcenterMember ucenterMember) {
        String token = memberService.login(ucenterMember);
        return ResultCommon.success().setData("token", token);
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/memberInfo")
    @ApiOperation("根据token获取用户信息")
    public UcenterMember getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwt(request);
        UcenterMember memberInfo = memberService.getById(memberId);
        if (memberInfo == null) {
            return null;
        }
        memberInfo.setPassword(null);
        return memberInfo;
    }

    /**
     * Feign接口，根据id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/memberInfo/{id}")
    @ApiOperation("Feign接口，根据id获取用户信息")
    public UcenterMember getMemberInfo(@PathVariable("id") Long id) {
        UcenterMember memberInfo = memberService.getById(id);
        if (memberInfo == null) {
            return null;
        }
        memberInfo.setPassword(null);
        return memberInfo;
    }

    /**
     * 注册新账户
     *
     * @param registerVo
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("注册新账户")
    public UcenterMember register(@RequestBody MemberRegisterVo registerVo) {
        UcenterMember createdMember = memberService.register(registerVo);
        return createdMember;
    }

    /**
     * 统计某日的注册人数
     * @param date 日期字符串，格式应为 yyyy-MM-dd
     * @return
     */
    @GetMapping("/stat/{date}")
    @ApiOperation("统计某日的注册人数，日期字符串格式应为yyyy-MM-dd")
    public Integer statRegister(@PathVariable("date") String date) {
        return memberService.statRegister(date);
    }

}

