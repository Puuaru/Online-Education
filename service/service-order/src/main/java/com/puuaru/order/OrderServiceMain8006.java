package com.puuaru.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: OrderServiceMain8006
 * @Author: puuaru
 * @Date: 2023/2/21
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableDiscoveryClient
public class OrderServiceMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceMain8006.class, args);
    }
}
