package com.kano.security.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <pre>
 * @title com.soranico.security.service.App
 * @description
 *        <pre>
 *          登录,鉴权,授权服务提供者
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@MapperScan("com.kano.security.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        application.run(args);
    }
}
