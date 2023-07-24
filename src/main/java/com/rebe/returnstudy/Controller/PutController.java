package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.DTO.MemberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class PutController {

    @PutMapping("/member/update")
    public MemberDto registerMember(@RequestBody MemberDto memberDto){
        System.out.println(memberDto.toString());
        return memberDto;

    }

}
