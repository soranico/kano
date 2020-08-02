package com.kano.util;

import com.alibaba.fastjson.JSONObject;
import com.kano.constants.HttpReplyEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * @title com.kano.util.HttpReply
 * @description
 *        <pre>
 *          HTTP请求响应工具
 *          {
 *              "status":执行结果,
 *              "data":响应数据,
 *              "message":执行结果描述
 *          }
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/1
 *
 * </pre>
 */
public class HttpReply {
    private static final String DATA="data";
    private static final String STATUS="status";
    private static final String MESSAGE="message";

    /**
     * 获取当前HTTP请求的HttpServletResponse
     * @return HttpServletResponse
     */
    private static HttpServletResponse currentResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 请求成功，执行结果符合预期响应
     * @param data 响应数据
     * @return 返回客户端数据
     */
    public static Object success200(Object data){
        currentResponse().setStatus(HttpServletResponse.SC_OK);
        JSONObject reply=new JSONObject(4);
        return reply.fluentPut(DATA,data).
                fluentPut(STATUS, HttpReplyEnum.SUCCESS.status())
                .fluentPut(MESSAGE, HttpReplyEnum.SUCCESS.message());
    }

    /**
     * 请求并成功创建
     * @param data
     * @return
     */
    public static Object success201(Object data){
        currentResponse().setStatus(HttpServletResponse.SC_CREATED);
        JSONObject reply=new JSONObject(4);
        return reply.fluentPut(DATA,data).
                fluentPut(STATUS, HttpReplyEnum.SUCCESS.status())
                .fluentPut(MESSAGE, HttpReplyEnum.SUCCESS.message());
    }

    /**
     * 请求到达但执行失败
     * @param data
     * @return
     */
    public static Object failed400(Object data){
        currentResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
        JSONObject reply=new JSONObject(4);
        return reply.fluentPut(DATA,data).
                fluentPut(STATUS, HttpReplyEnum.FAILED.status())
                .fluentPut(MESSAGE, HttpReplyEnum.FAILED.message());
    }
}
