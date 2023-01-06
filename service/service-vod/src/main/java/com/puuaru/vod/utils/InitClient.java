package com.puuaru.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description: InitClient
 * @Author: puuaru
 * @Date: 2023/1/6
 */
public class InitClient {

    public static DefaultAcsClient initClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";    // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);    // 接入客户端
        return client;
    }
}
