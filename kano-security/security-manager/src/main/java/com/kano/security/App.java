package com.kano.security;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <pre>
 * @title com.kano.security.App
 * @description
 *        <pre>
 *          登录,鉴权,授权处理外部请求
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@SpringBootApplication
@EnableWebFluxSecurity
@EnableDubbo
public class App {
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        application.run(args);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http.httpBasic()
                .and().authorizeExchange().pathMatchers("/auth/**").permitAll()
                .and().authorizeExchange().pathMatchers("/**").authenticated()
                .and().formLogin().disable()
                .csrf().disable()
                .logout().disable().build();
    }


}
