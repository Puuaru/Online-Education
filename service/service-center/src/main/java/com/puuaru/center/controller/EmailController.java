package com.puuaru.center.controller;

import com.puuaru.center.service.EmailService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: EmailController
 * @Author: puuaru
 * @Date: 2023/1/30
 */
@RestController
@RequestMapping("/center/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 向指定用户邮箱发送注册验证码
     * @param target
     * @return
     */
    @GetMapping("/{target}")
    @ApiOperation("向指定用户邮箱发送注册验证码")
    public ResultCommon sendMail(@PathVariable("target") String target) {
        String code = emailService.sendMail(target);
        return ResultCommon.success().setData("code", code);
    }
}
