package com.kano.security.service;

import com.kano.security.entity.dto.MemberDO;

/**
 * <pre>
 * @title com.kano.security.service.MemberService
 * @description
 *        <pre>
 *          memberService
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
public interface MemberService {

    MemberDO findByName(String name);
}
