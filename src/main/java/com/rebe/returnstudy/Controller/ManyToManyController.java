package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.Service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManyToManyController {

    private final PostLikeService postLikeService;

    @GetMapping("many-to-many-save")
    public void Save(){
        postLikeService.SaveMemberAndPost();
    }

    @GetMapping("many-to-many-read")
    public void Read(){
        postLikeService.ReadPostLike();
    }
}
