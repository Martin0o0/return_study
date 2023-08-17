package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Entity.PostLike;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostLikeRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void SaveMemberAndPost(){
        //회원 1 저장
        Member member1 = new Member();
        member1.setStudentId(2019102236);
        member1.setName("최현영");
        member1.setGeneration("32nd");
        member1.setClub("RETURN");
        memberRepository.save(member1);

        //회원 2 저장
        Member member2 = new Member();
        member2.setStudentId(2019000000);
        member2.setName("홍길동");
        member2.setGeneration("32nd");
        member2.setClub("RETURN");
        memberRepository.save(member2);

        //회원 3 저장
        Member member3 = new Member();
        member3.setStudentId(2023000000);
        member3.setName("김나박이");
        member3.setGeneration("36th");
        member3.setClub("RETURN");
        memberRepository.save(member3);

        //게시글 1 저장
        Post post1 = new Post();
        post1.setTitle("이번에 태풍온다던데");
        post1.setContent("이번에 카눈이라는 어마무시한 태풍이 북상한데");
        post1.setMember(member1);
        postRepository.save(post1);

        //게시글 2 저장
        Post post2 = new Post();
        post2.setTitle("갑자기 추워졌다");
        post2.setContent("태풍 지나가는 길에 더위 좀 데려갔으면");
        post2.setMember(member2);
        postRepository.save(post2);

        //멤버 2가 게시글 1에 좋아요를 눌렀다.
        PostLike postLike1 = new PostLike();
        postLike1.setPost(post1);
        postLike1.setMember(member2);
        postLikeRepository.save(postLike1);

        //멤버 3이 게시글 1에 좋아요를 눌렀다.
        PostLike postLike2 = new PostLike();
        postLike2.setPost(post1);
        postLike2.setMember(member3);
        postLikeRepository.save(postLike2);


        //멤버 1이 게시글 2에 좋아요를 눌렀다.
        PostLike postLike3 = new PostLike();
        postLike3.setPost(post2);
        postLike3.setMember(member1);
        postLikeRepository.save(postLike3);


        //회원 1이 좋아요를 누른 게시글 목록
        log.info(postLikeRepository.findAllByMemberId(member1.getId()).stream().map(postLike -> postLike.getPost()).collect(Collectors.toList()).toString());

        //게시글 1에 좋아요를 누른 회원 목록
        log.info(postLikeRepository.findAllByPostId(post1.getId()).stream().map(postLike -> postLike.getMember()).collect(Collectors.toList()).toString());
    }



}
