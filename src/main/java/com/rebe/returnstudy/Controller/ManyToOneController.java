package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.Service.MemberService;
import com.rebe.returnstudy.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManyToOneController {

    private final PostService postService;

    private final MemberService memberService;

    @GetMapping("/many-to-one")
    public void ManyToOneTest(){
        postService.SaveAndReadPostWriter();
    }

    @GetMapping("/many-to-one-bidirect")
    public void ManyToOneBidirect(){
        memberService.SaveAndReadMemberWithPost();

    }
    @GetMapping("/many-to-one-post")
    public void ManyToOne(){
        memberService.ReadMemberPost(1L);
    }
}
