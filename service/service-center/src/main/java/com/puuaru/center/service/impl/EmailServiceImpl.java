package com.puuaru.center.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.entity.UcenterMember;
import com.puuaru.center.mapper.ThreePartyPropertiesMapper;
import com.puuaru.center.service.EmailService;
import com.puuaru.center.service.UcenterMemberService;
import com.puuaru.center.entity.ThreePartyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @Description: EmailServiceImpl
 * @Author: puuaru
 * @Date: 2023/1/29
 */
@Service
public class EmailServiceImpl extends ServiceImpl<ThreePartyPropertiesMapper, ThreePartyProperties> implements EmailService {
    private final JavaMailSenderImpl sender;
    private final TemplateEngine templateEngine;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UcenterMemberService memberService;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl sender, TemplateEngine templateEngine, RedisTemplate redisTemplate, UcenterMemberService memberService) {
        this.sender = sender;
        this.templateEngine = templateEngine;
        this.redisTemplate = redisTemplate;
        this.memberService = memberService;
    }

    @PostConstruct
    public void init() {
        QueryWrapper<ThreePartyProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "email");
        ThreePartyProperties email = super.getOne(wrapper);
        sender.setUsername(email.getClientId());
        sender.setPassword(email.getSecret());
    }

    /**
     * 向用户指定邮箱发送注册验证码
     * @param target
     * @return 验证码
     */
    public String sendMail(String target) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", target);
        if (memberService.count(wrapper) > 0) {
            throw new RuntimeException("该邮箱已被注册");
        }

        // 生成邮件内容
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
        Context context = new Context();
        String code = RandomUtil.randomNumbers(6);
        context.setVariable("code", code);
        String content = templateEngine.process("code.html", context);
        try {
            msgHelper.setFrom(sender.getUsername());
            msgHelper.setTo(target);
            msgHelper.setSubject("验证码");
            msgHelper.setText(content, true);
        } catch (MessagingException e) {
            throw new RuntimeException("当前服务器正忙，请稍后再试", e);
        }
        redisTemplate.opsForValue().set(target, code, 5, TimeUnit.MINUTES);
        sender.send(msg);

        return code;
    }
}
