package com.puuaru.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.vod.mapper.VodMapper;
import com.puuaru.vod.properties.VodProperties;
import com.puuaru.vod.utils.InitClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VodService extends ServiceImpl<VodMapper, VodProperties> {
    @SneakyThrows
    private VodProperties getProperties() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("name", "aliyun");
        VodProperties properties = this.getOne(wrapper);
        return properties;
    }

    @SneakyThrows
    public String uploadVideo(MultipartFile video) {
        VodProperties properties = getProperties();
        String fileName = video.getOriginalFilename();
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream inputStream = video.getInputStream();
        UploadStreamRequest request = new UploadStreamRequest(properties.getAccessKeyId(),
                properties.getAccessKeySecret(),
                title,
                fileName,
                inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        return response.getVideoId();
    }

    @SneakyThrows
    public Boolean deleteSourceVideo(String videoId) {
        VodProperties properties = getProperties();
        DefaultAcsClient client = InitClient.initClient(properties.getAccessKeyId(), properties.getAccessKeySecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        DeleteVideoResponse response = client.getAcsResponse(request);
        if (response.getForbiddenVideoIds().isEmpty() && response.getNonExistVideoIds().isEmpty()) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public String getPlayAuth(String videoId) {
        VodProperties properties = getProperties();
        DefaultAcsClient client = InitClient.initClient(properties.getAccessKeyId(), properties.getAccessKeySecret());
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId(videoId);
        response = client.getAcsResponse(request);

        return response.getPlayAuth();
    }
}
