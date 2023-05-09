package com.puuaru.config;

import com.puuaru.filter.JwtAuthenticationFilter;
import com.puuaru.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: SecurityConfig
 * @Author: puuaru
 * @Date: 2023/5/6
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RedisTemplate redisTemplate;

    @Autowired
    public SecurityConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                // 放行 swagger
                .authorizeRequests().antMatchers("/swagger-ui/**", "/v2/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new LoginFilter(redisTemplate, authenticationManager()))
                .addFilterBefore(new JwtAuthenticationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class)
        ;

    }
}
