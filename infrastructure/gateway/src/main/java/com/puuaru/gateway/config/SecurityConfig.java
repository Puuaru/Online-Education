package com.puuaru.gateway.config;

import com.puuaru.gateway.security.JwtAccessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Description: SecurityConfig
 * @Author: puuaru
 * @Date: 2023/6/9
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private JwtAccessManager jwtAccessManager;
    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeExchange().anyExchange().access(jwtAccessManager)
        ;

        return http.build();
    }
}
