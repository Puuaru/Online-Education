package com.puuaru.center.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.entity.ThreePartyProperties;
import com.puuaru.center.mapper.ThreePartyPropertiesMapper;
import com.puuaru.center.service.GithubService;
import com.puuaru.helpers.RedirectHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description: GitHubServiceImpl
 * @Author: puuaru
 * @Date: 2023/2/1
 */
@Service
public class GitHubServiceImpl extends ServiceImpl<ThreePartyPropertiesMapper, ThreePartyProperties> implements GithubService {
    private ThreePartyProperties properties;

    @PostConstruct
    public void init() {
        QueryWrapper<ThreePartyProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "github");
        properties = super.getOne(wrapper);
    }

    /**
     * 通过Github OAuth完成第三方登录
     *
     * @return 用于重定向的URL
     */
    @Override
    public String login() {
        String baseUrl = "https://github.com/login/oauth/authorize";
        RedirectHelper redirectHelper = new RedirectHelper(baseUrl);
        String redirectUrl = redirectHelper.addParam("client_id", properties.getClientId())
                .addParam("state", IdUtil.randomUUID().substring(0, 10))
                .getUrl();
        return redirectUrl;
    }
}
