package com.kano.security.converter;

import com.alibaba.fastjson.JSON;
import com.kano.security.entity.dto.MemberDO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <pre>
 * @title com.kano.security.manager.converter.PasswordAuthenticationConverter
 * @description
 *        <pre>
 *          密码登录解析数据
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@Component
public class PasswordAuthenticationConverter implements ServerAuthenticationConverter {

    /**
     * 读取请求题转为认证类
     * @param exchange
     * @return
     */
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.create(sink->{
            // 当有数据订阅消息，并解析
            exchange.getRequest().getBody().subscribe(data->{
                String body = data.toString(StandardCharsets.UTF_8);
                MemberDO member=JSON.parseObject(body,MemberDO.class);
                Authentication authentication=new UsernamePasswordAuthenticationToken(member.getName(),member.getPassword());
                sink.success(authentication);
            });
        });
    }



}
