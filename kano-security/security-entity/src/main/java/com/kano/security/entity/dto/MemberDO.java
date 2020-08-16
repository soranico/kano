package com.kano.security.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * @title com.kano.security.entity.dto.MemberDO
 * @description
 *        <pre>
 *          memberDO
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@Data
public class MemberDO implements Serializable {

    private Long id;
    private String name;
    private String password;

}
