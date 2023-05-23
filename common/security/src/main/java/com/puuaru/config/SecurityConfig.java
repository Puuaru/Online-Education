package com.puuaru.config;

import com.puuaru.filter.JwtAuthenticationFilter;
import com.puuaru.filter.LoginFilter;
import com.puuaru.handler.AccessDeniedHandlerImpl;
import com.puuaru.handler.TokenLogoutHandler;
import com.puuaru.handler.UnAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                // 配置无状态，否则会因cookie影响认证导致无token的情况下也有可能通过
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 放行 swagger，并在编写测试阶段放行acl模块
                .authorizeRequests().antMatchers("/swagger-ui/**", "/v2/**", "/swagger-resources/**", "/webjars/**", "/acl/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/acl/index/logout").addLogoutHandler(new TokenLogoutHandler(redisTemplate))
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new UnAuthHandler())
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .and()
                .addFilter(new LoginFilter(redisTemplate, authenticationManager()))
                .addFilterBefore(new JwtAuthenticationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class);
    }
}
