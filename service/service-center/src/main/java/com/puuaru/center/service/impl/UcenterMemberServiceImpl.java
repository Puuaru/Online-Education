package com.puuaru.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.center.entity.UcenterMember;
import com.puuaru.center.mapper.UcenterMemberMapper;
import com.puuaru.center.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.vo.MemberRegisterVo;
import com.puuaru.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    private final RedisTemplate redisTemplate;

    @Autowired
    public UcenterMemberServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
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
        if (ObjectUtils.isEmpty(user) || !Objects.equals(user.getPassword(), loginPassword)) {
            throw new RuntimeException("邮箱或密码不正确");
        }
        if (user.getIsDisabled()) {
            throw new RuntimeException("当前用户不可用");
        }

        return JwtUtils.generateJwt(user.getId(), user.getNickname());
    }

    @Override
    public UcenterMember register(MemberRegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String email = registerVo.getEmail();
        String code = registerVo.getCode();

        Object verification = redisTemplate.opsForValue().get(email);
        if (!Objects.equals(verification, code)) {
            throw new RuntimeException("注册验证码不匹配！");
        }

        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setPassword(password);
        member.setEmail(email);
        member.setAvatar("https://online-education-puuaru.oss-cn-shenzhen.aliyuncs.com/2023/01/30/96ed892eb143463e92da0f9a6c743f59avataaars.png");
        super.save(member);

        return member;
    }
}
