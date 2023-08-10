package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.DTO.MemberDto;
import com.rebe.returnstudy.DTO.MemberResponseDto;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service //서비스 레이어의 클래스에 사용하는 컴포넌트 시리즈 어노테이션
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;


    //Create
    public MemberResponseDto saveMember(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setStudentId(memberDto.getStudentId());
        member.setGeneration(memberDto.getGeneration());
        member.setClub(memberDto.getClub());

       memberRepository.save(member);
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setStudentId(member.getStudentId());
        memberResponseDto.setClub(member.getClub());
        memberResponseDto.setGeneration(member.getGeneration());

        return memberResponseDto;

    }

    //Read
    public MemberResponseDto getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.get().getId());
            memberResponseDto.setStudentId(member.get().getStudentId());
            memberResponseDto.setName(member.get().getName());
            memberResponseDto.setGeneration(member.get().getGeneration());
            memberResponseDto.setClub(member.get().getClub());

            return memberResponseDto;
        } else {
            return null;
        }
    }

    //Update
    public MemberResponseDto updateMember(Long id, MemberDto memberDto) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member updateMember = member.get();
            updateMember.setStudentId(memberDto.getStudentId());
            updateMember.setClub(memberDto.getClub());
            updateMember.setGeneration(memberDto.getGeneration());
            updateMember.setName(memberDto.getName());

            memberRepository.save(updateMember);

            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(updateMember.getId());
            memberResponseDto.setStudentId(updateMember.getStudentId());
            memberResponseDto.setName(updateMember.getName());
            memberResponseDto.setGeneration(updateMember.getGeneration());
            memberResponseDto.setClub(updateMember.getClub());

            return memberResponseDto;
        } else {
            return null;
        }
    }

    //Delete
    public boolean isDeleteMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return true;
        } else {
            return false;
        }
    }









    public void SaveAndReadMemberWithPost(){
        Member member = new Member();
        member.setStudentId(2019102236);
        member.setName("최현영");
        member.setGeneration("32nd");
        member.setClub("RETURN");
        memberRepository.save(member);
        Post post1 = new Post();
        post1.setTitle("이번에 태풍온다던데");
        post1.setContent("이번에 카눈이라는 어마무시한 태풍이 북상한데");
        post1.setMember(member);

        Post post2 = new Post();
        post2.setTitle("경희대는 컴공 동아리는 리턴이 최고야!");
        post2.setContent("아님 말고 ㅋ");
        post2.setMember(member);
        postRepository.save(post1);
        postRepository.save(post2);
    }

    public void ReadMemberPost(long id){
        System.out.println(memberRepository.findById(id).get().getPosts());
    }





}
