package com.puuaru.vod.controller;

import com.puuaru.utils.ResultCommon;
import com.puuaru.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: VodController
 * @Author: puuaru
 * @Date: 2023/1/6
 */
@RestController
@RequestMapping("/vod")
@CrossOrigin
public class VodController {
    private final VodService vodService;

    @Autowired
    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    /**
     * 上传视频
     * @param video
     * @return
     */
    @PostMapping("")
    @ApiOperation("上传视频")
    public ResultCommon uploadVideo(@RequestPart("file") MultipartFile video) {
        String videoId = vodService.uploadVideo(video);
        return ResultCommon.success().setData("videoId", videoId);
    }

    /**
     * 删除视频
     * @param videoSourceId
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除视频")

    public Boolean deleteSourceVideo(@PathVariable("id") String videoSourceId) {
        Boolean result = vodService.deleteSourceVideo(videoSourceId);
        return result;
    }

    /**
     * 获取加密视频的播放凭证
     * @param videoSourceId
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("获取加密视频的播放凭证")
    public ResultCommon getPlayAuth(@PathVariable("id") String videoSourceId) {
        String playAuth = vodService.getPlayAuth(videoSourceId);
        return ResultCommon.success().setData("playAuth", playAuth);
    }

}
