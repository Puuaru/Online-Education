package com.puuaru.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: GatewayMain80
 * @Author: puuaru
 * @Date: 2023/4/7
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
public class GatewayMain80 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain80.class, args);
    }
}
