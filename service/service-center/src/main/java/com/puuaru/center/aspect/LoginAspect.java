package com.puuaru.center.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final RedisTemplate redisTemplate;

    private final String REDIS_HASH_NAME = "count_stat";
    private final String REDIS_HASH_KEY = "login_num";

    private final int CACHE_THRESHOLD = 128;

    @Autowired
    public LoginAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge, RedisTemplate redisTemplate) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("execution(public * com.puuaru.center.controller.UcenterMemberController.login(..))")
    public void localLogin() {
    }

    @Pointcut("execution(public * com.puuaru.center.controller.GithubController.login(..))")
    public void githubLogin() {
    }

    @SneakyThrows
    @Before("localLogin() || githubLogin()")
    public void loginCountHandler() {
        Integer loginCount = (Integer) redisTemplate.opsForHash().get(REDIS_HASH_NAME, REDIS_HASH_KEY);
        // 读取并更新计数，通过位运算求余
        loginCount = loginCount == null ? 1 : (loginCount + 1) & (CACHE_THRESHOLD - 1);
        // 更新计数器
        redisTemplate.opsForHash().put(REDIS_HASH_NAME, REDIS_HASH_KEY, loginCount);
        HashMap<String, String> params = new HashMap<>();
        params.put("type", "login_num");
        params.put("date", LocalDate.now(ZoneId.of("GMT-8")).toString());
        if (loginCount == 0) {
            // 到达阈值，则发送消息通知 Statistics 模块更新数据
            threadPoolExecutor.execute(() -> {
                streamBridge.send("loginCount-out-0", MessageBuilder.withPayload(params).build());
            });
        }
    }
}
