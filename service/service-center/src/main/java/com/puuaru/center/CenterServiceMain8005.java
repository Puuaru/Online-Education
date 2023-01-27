package com.puuaru.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: CenterServiceMain8005
 * @Author: puuaru
 * @Date: 2023/1/27
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableFeignClients
public class CenterServiceMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(CenterServiceMain8005.class, args);
    }
}
