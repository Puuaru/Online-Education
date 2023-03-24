package com.puuaru.vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ThreadPoolConfig
 * @Author: puuaru
 * @Date: 2023/3/24
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                20,
                30L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15));
        return threadPoolExecutor;
    }
}
