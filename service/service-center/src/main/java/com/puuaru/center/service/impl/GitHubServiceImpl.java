package com.puuaru.center.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.entity.ThreePartyProperties;
import com.puuaru.servicebase.entity.UcenterMember;
import com.puuaru.center.mapper.ThreePartyPropertiesMapper;
import com.puuaru.center.service.GithubService;
import com.puuaru.center.service.UcenterMemberService;
import com.puuaru.helpers.UrlHelper;
import com.puuaru.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Description: GitHubServiceImpl
 * @Author: puuaru
 * @Date: 2023/2/1
 */
@Service
public class GitHubServiceImpl extends ServiceImpl<ThreePartyPropertiesMapper, ThreePartyProperties> implements GithubService {
    private ThreePartyProperties properties;
    private final UcenterMemberService memberService;

    @Autowired
    public GitHubServiceImpl(UcenterMemberService memberService) {
        this.memberService = memberService;
    }

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
        UrlHelper urlHelper = new UrlHelper(baseUrl);
        String redirectUrl = urlHelper.addParam("client_id", properties.getClientId())
                .addParam("state", IdUtil.randomUUID().substring(0, 10))
                .getUrl();
        return redirectUrl;
    }

    /**
     * Github OAuth 回调处理器，token置于请求参数
     * @param code
     * @return
     */
    @Override
    public String callbackHandler(String code) {
        String baseTokenUrl = "https://github.com/login/oauth/access_token";
        String baseRedirectUrl = "http://localhost:3000";
        UrlHelper tokenUrlHelper = new UrlHelper(baseTokenUrl);
        UrlHelper redirectHelper = new UrlHelper(baseRedirectUrl);
        String tokenUrl = tokenUrlHelper.addParam("client_id", properties.getClientId())
                .addParam("client_secret", properties.getSecret())
                .addParam("code", code)
                .getUrl();
        String tokenInfo = HttpUtil.get(tokenUrl);
        String accessToken = tokenInfo.split("&")[0].split("=")[1];
        String userString = HttpRequest.get("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .execute().body();
        Map<String, Object> userMap = JSONObject.parseObject(userString);
        UcenterMember member = memberService.handleGithubUser(userMap);
        String token = JwtUtils.generateJwt(member.getId(), member.getNickname());
        String redirectUrl = redirectHelper.addParam("token", token).getUrl();
        return redirectUrl;
    }

}
