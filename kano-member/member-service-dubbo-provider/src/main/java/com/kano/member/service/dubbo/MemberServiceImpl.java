package com.kano.member.service.dubbo;

import com.kano.member.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <pre>
 * @title com.kano.member.provider.MemberServiceImpl
 * @description
 *        <pre>
 *          dubbo测试
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/7/26
 *
 * </pre>
 */
@DubboService
public class MemberServiceImpl implements MemberService {
    @Override
    public String provider(String message) {
        return "kano = "+message;
    }
}
