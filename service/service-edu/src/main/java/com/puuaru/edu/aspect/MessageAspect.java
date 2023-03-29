package com.puuaru.edu.aspect;

import com.puuaru.edu.service.EduCourseService;
import com.puuaru.servicebase.vo.CourseFrontInfo;
import com.puuaru.utils.ResultCommon;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
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
    private static final String COURSE_HASH_NAME = "count_course";
    private static final String COURSE_VIEW_COUNTER = "course_view_num";
    private static final int COURSE_VIEW_CACHE_THRESHOLD = 128;

    private final ThreadPoolExecutor threadPoolExecutor;
    private final StreamBridge streamBridge;
    private final RedisTemplate redisTemplate;
    private final EduCourseService courseService;

    @Autowired
    public MessageAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge, RedisTemplate redisTemplate, EduCourseService courseService) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
        this.redisTemplate = redisTemplate;
        this.courseService = courseService;
    }

    @Pointcut("execution(public * com.puuaru.edu.controller.FrontCourseController.getCourseFrontInfo(..))")
    public void viewCourse() {
    }

    @SneakyThrows
    @Around("viewCourse()")
    public ResultCommon courseViewCountHandler(ProceedingJoinPoint proceedingJoinPoint) {
        String courseId = ((Long) proceedingJoinPoint.getArgs()[0]).toString();
        ResultCommon proceed = (ResultCommon) proceedingJoinPoint.proceed();
        Integer statCourseViewCount = updateCounter(STAT_HASH_NAME, COURSE_VIEW_COUNTER, COURSE_VIEW_CACHE_THRESHOLD);
        Integer selectedCourseView = updateCounter(COURSE_HASH_NAME, courseId, COURSE_VIEW_CACHE_THRESHOLD);
        System.out.println(selectedCourseView);
        if (selectedCourseView == 0) {
            courseService.updateCourseView(courseId, COURSE_VIEW_CACHE_THRESHOLD);
            selectedCourseView = COURSE_VIEW_CACHE_THRESHOLD;
        }
        if (statCourseViewCount == 0) {
            // 到达阈值，则发送消息通知 Statistics 模块更新数据
            HashMap<String, String> params = new HashMap<>();
            params.put("type", COURSE_VIEW_COUNTER);
            params.put("date", LocalDate.now(ZoneId.of("GMT-8")).toString());
            threadPoolExecutor.execute(() -> {
                streamBridge.send("updateStatistics-out-0", MessageBuilder.withPayload(params).build());
            });
        }
        CourseFrontInfo details = (CourseFrontInfo) proceed.getData().get("details");
        if (details != null) {
            details.setViewCount(details.getViewCount() + selectedCourseView);
            proceed.setData("details", details);
        }

        return proceed;
    }

    private Integer updateCounter(String hashName, String key, Integer threshold) {
        Integer count = (Integer) redisTemplate.opsForHash().get(hashName, key);
        // 读取并更新计数，通过位运算求余
        count = count == null ? 1 : (count + 1) & (threshold - 1);
        // 更新计数器
        redisTemplate.opsForHash().put(hashName, key, count);
        return count;
    }
}
