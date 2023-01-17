package com.puuaru.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: OssServiceMain8002
 * @Author: puuaru
 * @Date: 2022/11/26
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableDiscoveryClient
public class OssServiceMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssServiceMain8002.class, args);
    }
}
