package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.MemberDetails;
import com.rebe.returnstudy.Repository.MemberDetailsRepository;
import com.rebe.returnstudy.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsService {
    private final MemberRepository memberRepository;
    private final MemberDetailsRepository memberDetailsRepository;

    public void OneToOne(){

        Member member = new Member();
        member.setStudentId(2019102236);
        member.setName("최현영");
        member.setGeneration("32nd");
        member.setClub("RETURN");
        memberRepository.save(member);


        MemberDetails memberDetails = new MemberDetails();
        memberDetails.setMember(member); //위에서 저장한 member 인스턴스를 setter 메서드로 할당
        memberDetails.setActive(true);
        memberDetails.setEmail("chy0310@khu.ac.kr");
        memberDetails.setPhoneNumber("01095026088");
        memberDetails.setStatusMsg("날씨 미친거 아님? 너무 덥다.");
        memberDetailsRepository.save(memberDetails);


        log.info("saved Member Entity" + memberDetailsRepository.findById(memberDetails.getId())
                .get().getMember());

        log.info("saved Member Details Entity : " + memberDetailsRepository.findById(memberDetails.getId())
                .get());
    }



    public void OneToOneBidirect(){

        Member member = new Member();
        member.setStudentId(2019102236);
        member.setName("최현영");
        member.setGeneration("32nd");
        member.setClub("RETURN");
        memberRepository.save(member);
        log.info("저장한 회원 ID : " + member.getId());

        MemberDetails memberDetails = new MemberDetails();
        memberDetails.setActive(true);
        memberDetails.setEmail("chy0310@khu.ac.kr");
        memberDetails.setPhoneNumber("01095026088");
        memberDetails.setStatusMsg("날씨 미친거 아님? 너무 덥다.");
        memberDetails.setMember(member);
        memberDetailsRepository.save(memberDetails);
        log.info("저장한 회원 정보 ID : " + memberDetails.getId());

        log.info("saved Member Entity" + memberRepository.findById(member.getId()).get());

        log.info("saved Member Details Entity : " + memberRepository.findById(member.getId()).get().getMemberDetails());

    }
}
