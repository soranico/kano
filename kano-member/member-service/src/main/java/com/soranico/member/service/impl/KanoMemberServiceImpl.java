package com.soranico.member.service.impl;

import com.kano.member.service.KanoMemberService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <pre>
 * @title com.kano.member.provider.KanoMemberServiceImpl
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
public class KanoMemberServiceImpl implements KanoMemberService {
    @Override
    public String provider() {
        return "kano";
    }
}
