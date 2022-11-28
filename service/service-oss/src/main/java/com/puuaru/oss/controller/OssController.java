package com.puuaru.oss.controller;

import com.puuaru.oss.service.OssService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: OssController
 * @Author: puuaru
 * @Date: 2022/11/26
 */
@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("")
    @ApiOperation("上传图片")
    public ResultCommon uploadFile(@RequestPart("file") MultipartFile file) {
        return ResultCommon.success().setData("url", ossService.uploadFile(file));
    }
}
