package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.DTO.LoginRequestDto;
import com.rebe.returnstudy.DTO.LoginResponseDto;
import com.rebe.returnstudy.DTO.MemberRequestDto;
import com.rebe.returnstudy.DTO.MemberResponseDto;
import com.rebe.returnstudy.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/return")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/member/{id}")
    public ResponseEntity<MemberResponseDto> getStudentInfo(@PathVariable(value = "id") Long id ) {
        System.out.println("회원 번호 : " + id);
        MemberResponseDto memberResponseDto = memberService.getMember(id);

        if(memberResponseDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<MemberResponseDto> registerMember(@Valid @RequestBody MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.saveMember(memberRequestDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberResponseDto> registerMember(@PathVariable(value = "id") Long id, @Valid @RequestBody MemberRequestDto memberRequestDto){
        System.out.println(memberRequestDto.toString());
        MemberResponseDto memberResponseDto = memberService.updateMember(id, memberRequestDto);

        if(memberResponseDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberResponseDto);
        }
    }

    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity<Void> dropOutMember(@PathVariable(value = "id") Long id){
        if(memberService.isDeleteMember(id)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/orphan/{id}")
    public ResponseEntity<Void> orphanMemberDetails(@PathVariable(value = "id")Long id){
        memberService.MakeOrphanMeberDetail(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return memberService.login(loginRequestDto);
    }


    @GetMapping("/admin/members")
    public ResponseEntity<?> getMembers(){
        return memberService.getMembers();
    }
}
