package com.puuaru.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: AclServiceMain8008
 * @Author: puuaru
 * @Date: 2023/4/19
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.puuaru")
public class AclServiceMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(AclServiceMain8008.class, args);
    }
}
