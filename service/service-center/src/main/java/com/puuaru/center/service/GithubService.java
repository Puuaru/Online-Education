package com.puuaru.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.center.entity.ThreePartyProperties;

/**
 * @Description: GitHubService
 * @Author: puuaru
 * @Date: 2023/2/1
 */
public interface GithubService extends IService<ThreePartyProperties> {
    String login();
}
