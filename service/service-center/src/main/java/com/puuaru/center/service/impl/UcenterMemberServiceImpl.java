package com.puuaru.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.entity.UcenterMember;
import com.puuaru.center.mapper.UcenterMemberMapper;
import com.puuaru.center.service.UcenterMemberService;
import com.puuaru.center.vo.MemberRegisterVo;
import com.puuaru.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类，密码使用 BCrypt 强哈希方法加密
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    private final RedisTemplate redisTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UcenterMemberServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * 登录逻辑
     * @param loginInfo
     * @return
     */
    @Override
    public String login(UcenterMember loginInfo) {
        String loginEmail = loginInfo.getEmail();
        String loginPassword = loginInfo.getPassword();

        if (ObjectUtils.isEmpty(loginEmail) || ObjectUtils.isEmpty(loginPassword)) {
            throw new RuntimeException("邮箱或密码不能为空");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", loginEmail);
        UcenterMember user = super.getOne(wrapper);
        boolean match = bCryptPasswordEncoder.matches(loginPassword, user.getPassword());
        if (ObjectUtils.isEmpty(user) || !match) {
            throw new RuntimeException("邮箱或密码不正确");
        }
        if (user.getIsDisabled()) {
            throw new RuntimeException("当前用户不可用");
        }

        return JwtUtils.generateJwt(user.getId(), user.getNickname());
    }

    /**
     * 基础注册逻辑
     * @param registerVo
     * @return
     */
    @Override
    public UcenterMember register(MemberRegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String rawPassword = registerVo.getPassword();
        String email = registerVo.getEmail();
        String code = registerVo.getCode();

        Object verification = redisTemplate.opsForValue().get(email);
        if (!Objects.equals(verification, code)) {
            throw new RuntimeException("注册验证码不匹配！");
        }

        UcenterMember member = new UcenterMember();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setNickname(nickname);
        member.setPassword(encodedPassword);
        member.setEmail(email);
        member.setAvatar("https://online-education-puuaru.oss-cn-shenzhen.aliyuncs.com/2023/01/30/96ed892eb143463e92da0f9a6c743f59avataaars.png");
        super.save(member);

        return member;
    }
}
