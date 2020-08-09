package com.kano.route.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * @title com.kano.route.filter.KanoMethodCheckFilter
 * @description
 *        <pre>
 *          请求方法拦截
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/9
 *
 * </pre>
 */
@Component
public class KanoMethodCheckFilter implements GlobalFilter, Ordered {

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static String unsupportedMethod;

    static {
        JSONObject unsupportedData = new JSONObject(2);
        unsupportedMethod = JSON.toJSONString(
                unsupportedData.fluentPut("message", "REQUEST MUST GET OR POST"));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String method = exchange.getRequest().getMethod().name().toUpperCase();
        switch (method) {
            case POST:
            case GET:
                return chain.filter(exchange);
            default:
                return unsupportedMethod(exchange);
        }
    }

    /**
     * 不支持的请求方法
     *
     * @param exchange 请求
     * @return {
     * message:method must GET or POST
     * }
     */
    private Mono<Void> unsupportedMethod(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
        return response.writeWith(Flux.just(
                response.bufferFactory().wrap(
                        Unpooled.wrappedBuffer(unsupportedMethod.getBytes()).nioBuffer())));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
