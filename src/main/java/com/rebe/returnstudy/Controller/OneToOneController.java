package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.Service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneToOneController {

    private final MemberDetailsService memberDetailsService;

    @Autowired
    public OneToOneController(MemberDetailsService memberDetailsService){
        this.memberDetailsService = memberDetailsService;
    }
    @GetMapping("/one-to-one")
    public void OneToOneTest(){
        memberDetailsService.SaveAndReadMemberDetails();
    }

    @GetMapping("/one-to-one-bidirect")
    public void OneToOneBidirect(){
        memberDetailsService.SaveAndReadMember();
    }

}
