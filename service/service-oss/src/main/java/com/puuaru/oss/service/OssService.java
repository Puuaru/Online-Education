package com.puuaru.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.oss.properties.OssProperties;
import org.springframework.web.multipart.MultipartFile;

public interface OssService extends IService<OssProperties> {
    /**
     * 上传一个文件至oss
     * @param file
     * @return 文件上传后的URL
     */
    String uploadFile(MultipartFile file);
}
