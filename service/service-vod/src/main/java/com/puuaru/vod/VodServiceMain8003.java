package com.puuaru.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: VodServiceMain8003
 * @Author: puuaru
 * @Date: 2023/1/5
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
public class VodServiceMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodServiceMain8003.class, args);
    }
}
