package com.kano.application.autoconfigure.web.http.servlet;

import com.kano.application.autoconfigure.web.http.filter.KanoHttpMethodFilter;
import com.kano.application.autoconfigure.web.http.properties.HttpMethodProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * <pre>
 * @title com.kano.application.autoconfigure.web.http.servlet.HttpMethodAutoConfiguration
 * @description
 *        <pre>
 *          POST转为PUT DELETE自动配置
 *          spring有HiddenHttpMethodFilter可以获取参数
 *          本类先从header读取，读取不到再从参数读取
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/1
 *
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HttpMethodProperties.class)
@ConditionalOnProperty(name = "kano.http.method.enable", havingValue = "true")
@ConditionalOnMissingBean(value = HiddenHttpMethodFilter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class HttpMethodAutoConfiguration {
    private static final String HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";
    private final HttpMethodProperties properties;

    public HttpMethodAutoConfiguration(HttpMethodProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public KanoHttpMethodFilter httpMethodFilter() {
        KanoHttpMethodFilter methodFilter = new KanoHttpMethodFilter();
        if (properties.getHeader() != null && !properties.getHeader().isEmpty()) {
            return methodFilter.fluentSetMethod(properties.getHeader());
        }
        return methodFilter.fluentSetMethod(HTTP_METHOD_OVERRIDE);
    }
}
