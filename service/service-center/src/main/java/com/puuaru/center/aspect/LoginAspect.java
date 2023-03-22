package com.puuaru.center.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: LoginAspect
 * @Author: puuaru
 * @Date: 2023/3/22
 */
@Aspect
@Component
public class LoginAspect {
    private final ThreadPoolExecutor threadPoolExecutor;
    private final StreamBridge streamBridge;

    @Autowired
    public LoginAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
    }

    @Pointcut("execution(public * com.puuaru.center.controller.UcenterMemberController.login(..))")
    public void localLogin() {}
    @Pointcut("execution(public * com.puuaru.center.controller.GithubController.login(..))")
    public void githubLogin() {}
    @Pointcut("execution(public * com.puuaru.center.controller.UcenterMemberController.aspectTest())")
    public void testPoint() {}

    @SneakyThrows
    //@Before("localLogin() || githubLogin()")
    @Before("testPoint()")
    public void loginCountHandler() {
        streamBridge.send("loginCount-out-0", MessageBuilder.withPayload(LocalDate.now(ZoneId.of("GMT-8")).toString()).build());
        System.out.println("======== Aspect Finish ========");
    }
}
