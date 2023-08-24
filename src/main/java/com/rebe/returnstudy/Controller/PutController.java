package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.DTO.MemberRequestDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class PutController {

    @PutMapping("/member/update")
    public MemberRequestDto registerMember(@RequestBody MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto.toString());
        return memberRequestDto;

    }

}
