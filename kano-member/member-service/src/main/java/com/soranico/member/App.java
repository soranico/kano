package com.soranico.member;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <pre>
 * @title com.kano.member.App
 * @description
 *        <pre>
 *          kano
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/7/26
 *
 * </pre>
 */
@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class App {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }
}

