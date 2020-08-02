package com.kano.constants;

/**
 * <pre>
 * @title com.kano.constants.HttpReplyEnum
 * @description
 *        <pre>
 *          HTTP响应枚举
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/1
 *
 * </pre>
 */
public enum HttpReplyEnum {
    /**
     * 请求执行成功
     * e.g
     * 请求虽然成功，但是处理失败，响应状态码勿返200
     */
    SUCCESS(1000, "success"),

    FAILED(1001, "failed");
    int status;
    String message;
    HttpReplyEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public int status() {
        return this.status;
    }
    public String message() {
        return this.message;
    }
}
