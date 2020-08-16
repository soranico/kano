package com.kano.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kano.security.converter.PasswordAuthenticationConverter;
import com.kano.security.manager.PasswordAuthenticationManager;
import com.kano.security.token.JwtGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * <pre>
 * @title com.kano.security.manager.filter.PasswordAuthenticationFilter
 * @description
 *        <pre>
 *          密码登录拦截
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@Component
@Slf4j
public class PasswordAuthenticationFilter implements WebFilter {

    private static final String DEFAULT_PASSWORD_URL = "/auth/user";

    private static byte[] authenticationFailed;

    static {
        JSONObject failed = new JSONObject(4);
        failed.put("status", 1001);
        failed.put("message", "用户名或密码错误");
        authenticationFailed = JSON.toJSONString(failed).getBytes(StandardCharsets.UTF_8);
    }

    private final PasswordAuthenticationManager manager;
    private final PathPatternParserServerWebExchangeMatcher matcher;

    private final ServerAuthenticationConverter converter = new PasswordAuthenticationConverter();

    public PasswordAuthenticationFilter(@Value("${kano.auth.password:}") String url,
                                        PasswordAuthenticationManager manager) {
        this.manager = manager;
        this.matcher = new PathPatternParserServerWebExchangeMatcher(url.isEmpty() ? DEFAULT_PASSWORD_URL : url, HttpMethod.POST);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return this.matcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                .flatMap(matchResult -> this.converter.convert(exchange))
                // TODO 没有解析直接不传递，后续验证码登录再传递
//                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                // TODO 需要封装为成功和失败响应
                .flatMap(manager::authenticate)
                // TODO 需要修改为异常响应
                .flatMap(authentication -> authenticationSuccess(authentication, new WebFilterExchange(exchange, chain)));

    }

    /**
     * 认证成功响应 jwt 密钥和过期时间
     * TODO 需要单独封装，认证失败和成功分别提交事件处理
     *
     * @param authentication    认证结果
     * @param webFilterExchange filter
     * @return 响应数据
     */
    private Mono<Void> authenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        if (!authentication.isAuthenticated()) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            return response.writeWith(Flux.just(response.bufferFactory().wrap(authenticationFailed)));
        }

        log.info("authorities = {}", authentication.getAuthorities().toString());

        String token = JwtGenerate.generateToken(authentication.getName(), authentication.getAuthorities().toString());
        JSONObject data = new JSONObject(4);
        data.put("status", 1000);
        data.put(JwtGenerate.TOKEN, token);
        response.setStatusCode(HttpStatus.OK);
        return response.writeWith(Flux.just(response.bufferFactory().wrap(JSON.toJSONString(data).getBytes())));

    }


}
