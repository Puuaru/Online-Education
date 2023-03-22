package com.puuaru.statistic.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @Description: LoginCounter
 * @Author: puuaru
 * @Date: 2023/3/22
 */
@Component
public class LoginCounter {
    @Bean
    public Consumer<String> loginCount() {
        return date -> System.out.println("==========" + date + "==========");
    }
}
