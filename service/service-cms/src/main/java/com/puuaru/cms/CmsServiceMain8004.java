package com.puuaru.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: CmsServiceMain8004
 * @Author: puuaru
 * @Date: 2023/1/9
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
@EnableDiscoveryClient
public class CmsServiceMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(CmsServiceMain8004.class, args);
    }
}
