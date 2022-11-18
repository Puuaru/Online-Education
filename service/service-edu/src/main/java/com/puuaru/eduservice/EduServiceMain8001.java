package com.puuaru.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: EduServiceMain8001
 * @Author: puuaru
 * @Date: 2022/11/17
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.puuaru")
public class EduServiceMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(EduServiceMain8001.class, args);
    }
}
