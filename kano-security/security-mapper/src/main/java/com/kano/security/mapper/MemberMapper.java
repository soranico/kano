package com.kano.security.mapper;

import com.kano.security.entity.dto.MemberDO;

/**
 * <pre>
 * @title com.kano.security.mapper.MemberMapper
 * @description
 *        <pre>
 *          memberMapper
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
public interface MemberMapper {

    MemberDO findByName(String name);

}
