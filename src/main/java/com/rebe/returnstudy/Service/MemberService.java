package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.DTO.MemberDto;
import com.rebe.returnstudy.DTO.MemberResponseDto;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //서비스 레이어의 클래스에 사용하는 컴포넌트 시리즈 어노테이션
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired //의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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

}
