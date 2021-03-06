package com.kano.member.manager;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        application.run(args);
    }
}
