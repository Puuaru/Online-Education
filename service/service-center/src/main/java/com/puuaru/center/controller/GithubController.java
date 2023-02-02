package com.puuaru.center.controller;

import com.puuaru.center.service.GithubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: GithubController
 * @Author: puuaru
 * @Date: 2023/2/1
 */
@Controller
@RequestMapping("/center/github")
@CrossOrigin
public class GithubController {
    private final GithubService githubService;

    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    /**
     * 使用Github OAuth登录
     * @return  重定向到github
     */
    @GetMapping("")
    @ApiOperation("使用Github OAuth登录")
    public String login() {
        String redirectUrl = githubService.login();
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/callback")
    @ApiOperation("Github重定向")
    public String callback(String code) {
        String redirectUrl = githubService.callbackHandler(code);
        return "redirect:" + redirectUrl;
    }
}
