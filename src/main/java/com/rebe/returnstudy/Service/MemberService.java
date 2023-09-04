package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.DTO.MemberRequestDto;
import com.rebe.returnstudy.DTO.MemberResponseDto;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.MemberDetails;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Exception.CustomException;
import com.rebe.returnstudy.Exception.ErrCode;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //서비스 레이어의 클래스에 사용하는 컴포넌트 시리즈 어노테이션
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;


    //Create
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {

        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setStudentId(memberRequestDto.getStudentId());
        member.setGeneration(memberRequestDto.getGeneration());
        member.setClub(memberRequestDto.getClub());


        MemberDetails memberDetails = new MemberDetails();
        memberDetails.setStatusMsg(memberRequestDto.getStatusMsg());
        memberDetails.setPhoneNumber(memberRequestDto.getPhoneNumber());
        memberDetails.setEmail(memberRequestDto.getEmail());
        memberDetails.setActive(true);

        member.setMemberDetails(memberDetails);
        memberDetails.setMember(member);
        //양방향으로 서로 참조해주고
        memberRepository.save(member);
        //Member만 영속화시켜 저장해줌으로써 데이터베이스에 저장한다.
        //Member가 영속 객체로 놓여지게 된다면,
        //영속성 전이로 인해 MemberDetails도 같이 영속 상태에 놓여지게 되며 같이 저장된다.

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setStudentId(member.getStudentId());
        memberResponseDto.setClub(member.getClub());
        memberResponseDto.setGeneration(member.getGeneration());
        memberResponseDto.setIsActive(memberDetails.isActive());
        memberResponseDto.setEmail(memberDetails.getEmail());
        memberResponseDto.setPhoneNumber(memberDetails.getPhoneNumber());
        memberResponseDto.setStatusMsg(memberDetails.getStatusMsg());


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
            memberResponseDto.setPhoneNumber(member.get().getMemberDetails().getPhoneNumber());
            memberResponseDto.setIsActive(member.get().getMemberDetails().isActive());
            memberResponseDto.setEmail(member.get().getMemberDetails().getEmail());
            memberResponseDto.setStatusMsg(member.get().getMemberDetails().getStatusMsg());

            return memberResponseDto;
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_MEMBER, HttpStatus.NOT_FOUND);
        }
    }

    //Update
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Optional<Member> member = memberRepository.findById(id);
        //영속성 컨텍스트에 놓아 영속 객체로 두는데 이때 memberDetails도 같이 영속 상태로 둔다.
        if (member.isPresent()) {
            Member updateMember = member.get();
            updateMember.setStudentId(memberRequestDto.getStudentId());
            updateMember.setClub(memberRequestDto.getClub());
            updateMember.setGeneration(memberRequestDto.getGeneration());
            updateMember.setName(memberRequestDto.getName());

            MemberDetails memberDetails = new MemberDetails();
            memberDetails.setStatusMsg(memberDetails.getStatusMsg());
            memberDetails.setPhoneNumber(memberDetails.getPhoneNumber());
            memberDetails.setEmail(memberDetails.getEmail());
            memberDetails.setActive(true);

            memberRepository.save(updateMember);

            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(updateMember.getId());
            memberResponseDto.setStudentId(updateMember.getStudentId());
            memberResponseDto.setName(updateMember.getName());
            memberResponseDto.setGeneration(updateMember.getGeneration());
            memberResponseDto.setClub(updateMember.getClub());
            memberResponseDto.setIsActive(memberDetails.isActive());
            memberResponseDto.setEmail(memberDetails.getEmail());
            memberResponseDto.setPhoneNumber(memberDetails.getPhoneNumber());
            memberResponseDto.setStatusMsg(memberDetails.getStatusMsg());

            return memberResponseDto;
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_MEMBER, HttpStatus.NOT_FOUND);
        }
    }

    //Delete
    public boolean isDeleteMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            //member 삭제시, 연관된 memberDetails 엔티티도 삭제된다.
            return true;
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_MEMBER, HttpStatus.NOT_FOUND);
        }
    }


    public void SaveAndReadMemberWithPost(){
        Member member = new Member();
        member.setStudentId("2019102236");
        member.setName("최현영");
        member.setGeneration("32nd");
        member.setClub("RETURN");
        memberRepository.save(member);

        Post post1 = new Post();
        post1.setTitle("이번에 태풍온다던데");
        post1.setContent("이번에 카눈이라는 어마무시한 태풍이 북상한데");
        post1.setMember(member);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("경희대는 컴공 동아리는 리턴이 최고야!");
        post2.setContent("아님 말고 ㅋ");
        post2.setMember(member);
        postRepository.save(post2);

        log.info(memberRepository.findById(member.getId()).get().getPosts().toString());
    }




    public void MakeOrphanMeberDetail(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            //Member의 연관관계를 제거한다.
            member.get().setMemberDetails(null);
            memberRepository.save(member.get());
        }
    }







}
