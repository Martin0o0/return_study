package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    public void SaveAndReadPostWriter(){
        Member member = new Member();
        member.setStudentId(2019102236);
        member.setName("최현영");
        member.setGeneration("32nd");
        member.setClub("RETURN");
        memberRepository.save(member);
        log.info("저장한 회원 ID : " + member.getId());

        Post post1 = new Post();
        post1.setTitle("이번에 태풍온다던데");
        post1.setContent("이번에 카눈이라는 어마무시한 태풍이 북상한데");
        post1.setMember(member);
        postRepository.save(post1);
        log.info("저장한 게시글 ID : " + post1.getId());

        Post post2 = new Post();
        post2.setTitle("경희대는 컴공 동아리는 리턴이 최고야!");
        post2.setContent("아님 말고 ㅋ");
        post2.setMember(member);
        postRepository.save(post2);
        log.info("저장한 게시글 ID : " + post2.getId());

        log.info("saved post1 info : "  + postRepository.findById(post1.getId()).get());
        log.info("saved post2 info : "  + postRepository.findById(post2.getId()).get());
        log.info("writer info : " + postRepository.findById(post1.getId()).get().getMember().toString());

    }





}
