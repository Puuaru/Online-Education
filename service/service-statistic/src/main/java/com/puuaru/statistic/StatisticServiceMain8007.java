package com.puuaru.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: StatisticServiceMain8007
 * @Author: puuaru
 * @Date: 2023/3/3
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableFeignClients
@EnableScheduling
public class StatisticServiceMain8007 {
    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceMain8007.class, args);
    }
}
