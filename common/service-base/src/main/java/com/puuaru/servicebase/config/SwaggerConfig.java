package com.puuaru.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @Description: SwaggerConfig
 * @Author: puuaru
 * @Date: 2022/11/18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .select()
                .paths(Predicate.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api Documents of online-education backend application")
                .description("本文档为定义online-education的后端接口文档")
                .version("1.0")
                .contact(new Contact("Puuaru", null, "honghuizhang4w@gmail.com"))
                .build();
    }
}
