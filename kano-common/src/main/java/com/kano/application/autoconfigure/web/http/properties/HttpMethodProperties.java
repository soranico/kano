package com.kano.application.autoconfigure.web.http.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>
 * @title com.kano.application.autoconfigure.web.http.properties.HttpMethodProperties
 * @description
 *        <pre>
 *          读取配置文件
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/1
 *
 * </pre>
 */
@ConfigurationProperties(prefix = "kano.http.method")
@Setter
@Getter
public class HttpMethodProperties {
    private boolean enable;
    private String header;
}
