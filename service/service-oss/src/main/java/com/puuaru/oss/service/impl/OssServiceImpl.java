package com.puuaru.oss.service.impl;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.oss.mapper.PropertiesMapper;
import com.puuaru.oss.properties.OssProperties;
import com.puuaru.oss.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Description: OssServiceImpl
 * @Author: puuaru
 * @Date: 2022/11/26
 */
@Service
public class OssServiceImpl extends ServiceImpl<PropertiesMapper, OssProperties> implements OssService {

    @Value("${oss.resource}")
    private String ossResource;

    @Override
    public String uploadFile(MultipartFile file) {
        QueryWrapper<OssProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", ossResource);
        OssProperties properties = super.getOne(wrapper);

        String accessKeyId = properties.getKeyId();
        String accessKeySecret = properties.getKeySecret();
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        String filename = date + "/" + IdUtil.simpleUUID() + file.getOriginalFilename();    // 通过日期归类
        String fileUrl = "https://" + bucketName + "." + endpoint + "/" + filename;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try (InputStream inputStream = file.getInputStream()) {
            ossClient.putObject(bucketName, filename, inputStream);
            return fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return fileUrl;
        } finally {
            ossClient.shutdown();
        }
    }
}
