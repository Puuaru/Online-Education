package com.puuaru.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: OrderServiceMain8006
 * @Author: puuaru
 * @Date: 2023/2/21
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableFeignClients
public class OrderServiceMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceMain8006.class, args);
    }
}
