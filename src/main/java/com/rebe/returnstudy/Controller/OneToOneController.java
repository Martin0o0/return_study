package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.Service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OneToOneController {

    private final MemberDetailsService memberDetailsService;

    @GetMapping("/one-to-one")
    public void OneToOneTest(){
        memberDetailsService.OneToOne();
    }

    @GetMapping("/one-to-one-bidirect")
    public void OneToOneBidirect(){
        memberDetailsService.OneToOneBidirect();
    }

}
