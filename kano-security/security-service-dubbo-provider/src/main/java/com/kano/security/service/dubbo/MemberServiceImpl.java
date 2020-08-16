package com.kano.security.service.dubbo;

import com.kano.security.entity.dto.MemberDO;
import com.kano.security.mapper.MemberMapper;
import com.kano.security.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * @title com.kano.security.service.dubbo.MemberServiceImpl
 * @description
 *        <pre>
 *          MemberServiceImpl
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@DubboService
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDO findByName(String name) {
        return memberMapper.findByName(name);
    }
}
