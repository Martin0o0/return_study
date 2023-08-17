package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.Entity.Category;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Repository.CategoryRepository;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    public void OneToManyPostHashTagSave(){
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
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("비 많이 오네");
        post2.setContent("집에서 넷플리스 보는게 제일 안전함");
        post2.setMember(member);
        postRepository.save(post2);

        Category category = new Category();
        category.setCategory("기상속보");
        category.getPosts().addAll(List.of(post1, post2)); //리스트에 담아서 저장하자.
        categoryRepository.save(category);

        log.info("Category의 Posts : " + categoryRepository.findById(category.getId()).get().getPosts().toString());
    }


}
