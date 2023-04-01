package com.puuaru.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: BuyCountAspect
 * @Author: puuaru
 * @Date: 2023/3/31
 */
@Aspect
@Component
public class BuyCountAspect {
    private final ThreadPoolExecutor threadPoolExecutor;
    private final StreamBridge streamBridge;

    @Autowired
    public BuyCountAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
    }

    @Pointcut("execution(public * com.puuaru.order.controller.OrderController.saveOrder(..))")
    public void buyPointCut() {
    }

    @Before("buyPointCut()")
    public void sendMessage(JoinPoint joinPoint) {
        Long courseId = (Long) joinPoint.getArgs()[0];
        System.out.println(courseId);
        threadPoolExecutor.execute(() -> {
            streamBridge.send("updateCourseBuyCount-out-0", MessageBuilder.withPayload(courseId).build());
        });
    }
}
