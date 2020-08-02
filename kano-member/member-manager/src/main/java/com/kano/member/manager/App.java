package com.kano.member.manager;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * @title com.kano.member.manager.App
 * @description
 *        <pre>
 *          启动
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/2
 *
 * </pre>
 */
@SpringBootApplication
@EnableDubbo
public class App {
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
