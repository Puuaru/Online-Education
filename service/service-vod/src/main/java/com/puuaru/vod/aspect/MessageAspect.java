package com.puuaru.vod.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: MessageAspect
 * @Author: puuaru
 * @Date: 2023/3/24
 */
@Aspect
@Component
public class MessageAspect {
    private static final String STAT_HASH_NAME = "count_stat";
    private static final String VOD_HASH_NAME = "count_stat";
    private static final String VIDEO_VIEW_COUNTER = "video_view_num";
    private static final int VIDEO_VIEW_COUNTER_THRESHOLD = 128;
    private static final int VIDEO_VIEW_UPDATE_THRESHOLD = 64;

    private final ThreadPoolExecutor threadPoolExecutor;
    private final StreamBridge streamBridge;
    private final RedisTemplate redisTemplate;

    @Autowired
    public MessageAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge, RedisTemplate redisTemplate) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("execution(public * com.puuaru.vod.controller.VodController.getPlayAuth(..))")
    public void viewCourse() {
    }
    @SneakyThrows
    @Before("viewCourse()")
    public void registerCountHandler() {
        updateCounterWithNotification(VIDEO_VIEW_COUNTER, VIDEO_VIEW_COUNTER_THRESHOLD);
    }

    private void updateCounterWithNotification(String counterType, Integer threshold) {
        Integer count = (Integer) redisTemplate.opsForHash().get(STAT_HASH_NAME, counterType);
        // 读取并更新计数，通过位运算求余
        count = count == null ? 1 : (count + 1) & (threshold - 1);
        // 更新计数器
        redisTemplate.opsForHash().put(STAT_HASH_NAME, counterType, count);
        HashMap<String, String> params = new HashMap<>();
        params.put("type", counterType);
        params.put("date", LocalDate.now(ZoneId.of("GMT-8")).toString());
        if (count == 0) {
            // 到达阈值，则发送消息通知 Statistics 模块更新数据
            threadPoolExecutor.execute(() -> {
                streamBridge.send("updateStatistics-out-0", MessageBuilder.withPayload(params).build());
            });
        }
    }

    @Before("viewCourse()")
    public void singleVideoViewHandler(ProceedingJoinPoint joinPoint) {
        Integer videoId = (Integer) joinPoint.getArgs()[0];
        Integer count = (Integer) redisTemplate.opsForHash().get(VOD_HASH_NAME, videoId);
        // 读取并更新计数，通过位运算求余
        count = count == null ? 1 : (count + 1) & (VIDEO_VIEW_UPDATE_THRESHOLD - 1);
        // 更新计数器
        redisTemplate.opsForHash().put(STAT_HASH_NAME, videoId, count);
        if (count == 0) {
            // 到达阈值，则发送消息通知 Statistics 模块更新数据
            threadPoolExecutor.execute(() -> {
                streamBridge.send("videoViewCount-out-0", MessageBuilder.withPayload(videoId).build());
            });
        }
    }
}
