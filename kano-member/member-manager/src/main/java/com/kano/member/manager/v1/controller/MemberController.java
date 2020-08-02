package com.kano.member.manager.v1.controller;

import com.kano.member.bo.KanoBO;
import com.kano.member.service.MemberService;
import com.kano.util.HttpReply;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * @title com.kano.member.manager.v1.controller.MemberController
 * @description
 *        <pre>
 *          member-controller
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/2
 *
 * </pre>
 */
@RestController
@RequestMapping("/v1/member")
@Slf4j
public class MemberController {

    @DubboReference
    private MemberService memberService;

    @PostMapping("/operate")
    public Object create(@RequestBody KanoBO kano){
        try {
            log.info("operate create");
            String data = memberService.provider("create");
            return HttpReply.success201(data);
        }catch (Exception e){
            log.error("create",e);
            return HttpReply.failed400("心拍数♯0822");
        }
    }

    @PutMapping("/operate")
    public Object update(@RequestBody KanoBO kano){
        try {
            log.info("operate update");
            String data = memberService.provider("update");
            return HttpReply.success201(data);
        }catch (Exception e){
            log.error("update",e);
            return HttpReply.failed400("心拍数♯0822");
        }
    }

    @DeleteMapping("/operate")
    public Object delete(@RequestBody KanoBO kano){
        try {
            log.info("operate delete");
            String data = memberService.provider("delete");
            return HttpReply.success201(data);
        }catch (Exception e){
            log.error("delete",e);
            return HttpReply.failed400("心拍数♯0822");
        }
    }

    @GetMapping("/operate")
    public Object list(){
        try {
            log.info("operate list");
            String data = memberService.provider("list");
            return HttpReply.success201(data);
        }catch (Exception e){
            log.error("list",e);
            return HttpReply.failed400("心拍数♯0822");
        }
    }


}
