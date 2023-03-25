package com.puuaru.edu.rabbitmq;

import com.puuaru.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @Description: VideoViewCounter
 * @Author: puuaru
 * @Date: 2023/3/24
 */
@Component
public class VideoViewCounter {
    private final EduVideoService videoService;

    @Autowired
    public VideoViewCounter(EduVideoService videoService) {
        this.videoService = videoService;
    }

    @Bean
    public Consumer<String> updateVideoView() {
        return (sourceId) -> videoService.updateVideosViewBySourceId(sourceId);
    }
}
