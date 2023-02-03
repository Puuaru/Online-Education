package com.puuaru.center.service;

import com.puuaru.center.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.center.vo.MemberRegisterVo;

import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    String login(UcenterMember loginInfo);

    UcenterMember register(MemberRegisterVo registerVo);

    UcenterMember handleGithubUser(Map<String, Object> userMap);
}
