package com.kano.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <pre>
 * @title com.kano.route.App
 * @description
 *        <pre>
 *          路由分发启动
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/8
 *
 * </pre>
 */
@EnableDiscoveryClient
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }
}
