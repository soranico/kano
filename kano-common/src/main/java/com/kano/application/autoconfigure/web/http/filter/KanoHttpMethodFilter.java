package com.kano.application.autoconfigure.web.http.filter;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * <pre>
 * @title com.kano.application.autoconfigure.web.http.filter.KanoHttpMethodFilter
 * @description
 *        <pre>
 *          拦截http请求，转换请求格式
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/1
 *
 * </pre>
 */
public class KanoHttpMethodFilter extends OncePerRequestFilter {
    private static final String HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";

    private static final List<String> ALLOWED_METHODS =
            Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(),
                    HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));

    private String method = HTTP_METHOD_OVERRIDE;

    public KanoHttpMethodFilter fluentSetMethod(String method) {
        this.method = method;
        return this;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest requestToUse = request;
        if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
            String header = request.getHeader(this.method);
            if (StringUtils.isEmpty(header)) {
                header = request.getParameter(this.method);
            }
            if (StringUtils.hasLength(header)) {
                String method = header.toUpperCase(Locale.ENGLISH);
                if (ALLOWED_METHODS.contains(method)) {
                    requestToUse = new KanoHttpMethodRequestWrapper(request, method);
                }
            }
        }
        doFilter(requestToUse, response, filterChain);
    }

    private static class KanoHttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public KanoHttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
}
