package com.puuaru.edu.controller;


import com.puuaru.edu.entity.EduVideo;
import com.puuaru.edu.service.EduVideoService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/edu/video")
public class EduVideoController {

    private final EduVideoService videoService;

    @Autowired
    public EduVideoController(EduVideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 根据小节id获取小节信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据小节id获取小节信息")
    public EduVideo getVideoById(@PathVariable Long id) {
        EduVideo video = videoService.getById(id);
        return video;
    }

    /**
     * 添加小节
     * @param video
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加小节")
    public Boolean saveVideo(@RequestBody EduVideo video) {
        Boolean result = videoService.save(video);
        return result;
    }

    /**
     * 根据小节id更新小节信息
     * @param video
     * @return
     */
    @PutMapping("")
    @ApiOperation("根据小节id更新小节信息")
    public EduVideo updateVideo(@RequestBody EduVideo video) {
        videoService.updateById(video);
        return video;
    }

    /**
     * 根据小节id删除小节信息
     * TODO: VideoService中删除信息的同时删除视频
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据小节id删除小节信息")
    public Boolean removeVideo(@PathVariable Long id) {
        Boolean result = videoService.removeVideo(id);
        return result;
    }
}

