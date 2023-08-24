package com.rebe.returnstudy.Controller;


import com.rebe.returnstudy.DTO.MemberRequestDto;
import com.rebe.returnstudy.DTO.PostRequestDto;
import com.rebe.returnstudy.Service.PostLikeService;
import com.rebe.returnstudy.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/return")
public class PostController {
    private final PostService postService;

    private final PostLikeService postLikeService;

//
//    @PostMapping("/member/registration")
//    public MemberRequestDto registerMember(@RequestBody MemberRequestDto memberDto){
//        System.out.println(memberDto.toString());
//        return memberDto;
//
//    }


    @PostMapping("/member/registration")
    public ResponseEntity<MemberRequestDto> registerMember(@RequestBody MemberRequestDto memberRequestDto) {
        System.out.println(memberRequestDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(memberRequestDto);
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<?> createPost(@PathVariable(value = "id") Long id) {
        return postService.readPost(id);
    }


    //PostService
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        return postService.savePost(postRequestDto);
    }


    //PostUpdate
    @PutMapping("/post/{id}")
    public ResponseEntity<?> readPost(@PathVariable(value = "id") Long id, @Valid @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);

    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(value = "id") Long id) {
            return postService.deletePost(id);
    }

    @PostMapping("/post/{id}/like/{student-id}")
    public ResponseEntity<?> likePost(@PathVariable(value = "id")Long id,@PathVariable(value = "student-id")String studentId){
        return postLikeService.likePost(id, studentId);
    }

    @DeleteMapping("/post/{id}/unlike/{student-id}")
    public ResponseEntity<?> unlikePost(@PathVariable(value = "id")Long id,@PathVariable(value = "student-id")String studentId){
        return postLikeService.unlikePost(id, studentId);
    }

}
