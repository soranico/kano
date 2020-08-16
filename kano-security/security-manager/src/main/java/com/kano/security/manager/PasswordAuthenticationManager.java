package com.kano.security.manager;

import com.kano.security.entity.dto.MemberDO;
import com.kano.security.service.MemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * <pre>
 * @title com.kano.security.manager.PasswordAuthenticationManager
 * @description
 *        <pre>
 *          认证授权
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/15
 *
 * </pre>
 */
@Component
public class PasswordAuthenticationManager implements ReactiveAuthenticationManager {

    @DubboReference
    private MemberService memberService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String name = authentication.getName();
        MemberDO member = memberService.findByName(name);
        if (member == null) {
            authentication.setAuthenticated(false);
            return Mono.create(sink -> sink.success(authentication));
        }
        return Mono.create(sink -> {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    name, member.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
            );
            sink.success(auth);
        });

    }
}
