package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.DTO.PostRequestDto;
import com.rebe.returnstudy.DTO.PostResponseDto;
import com.rebe.returnstudy.Entity.Category;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Exception.CustomException;
import com.rebe.returnstudy.Exception.ErrCode;
import com.rebe.returnstudy.Repository.CategoryRepository;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;


    @Transactional
    public ResponseEntity<?> savePost(PostRequestDto postRequestDto, Principal principal) {
        Optional<Member> writer = memberRepository.findByStudentId(principal.getName());
        if (writer.isPresent()) {
            Post post = new Post();
            post.setMember(writer.get());

            post.setTitle(postRequestDto.getTitle());
            post.setContent(postRequestDto.getContent());

            postRepository.save(post);

            Optional<Category> category = categoryRepository.findByCategory(postRequestDto.getCategory());
            if (category.isPresent()) {
                category.get().getPosts().add(post);
                categoryRepository.save(category.get());
            } else {
                Category newCategory = new Category();
                newCategory.setCategory(postRequestDto.getCategory());
                newCategory.getPosts().add(post);
                categoryRepository.save(newCategory);
            }

            Map<String, Long> response = new HashMap<>();
            response.put("등록된 게시글 번호", post.getId());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<?> readPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setId(post.get().getId());
            postResponseDto.setTitle(post.get().getTitle());
            postResponseDto.setContent(post.get().getContent());
            postResponseDto.setStudentId(post.get().getMember().getStudentId());
            return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_POST, HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<?> updatePost(Long id, PostRequestDto postRequestDto) {
        Optional<Post> post = postRepository.findById(id);
        Optional<Member> member = memberRepository.findByStudentId(postRequestDto.getStudentId());
        if (member.isPresent()) {
            if (post.isPresent()) {


                post.get().setMember(member.get());
                post.get().setTitle(postRequestDto.getTitle());
                post.get().setContent(postRequestDto.getContent());

                postRepository.save(post.get());

                Optional<Category> category = categoryRepository.findByCategory(postRequestDto.getCategory());
                if (category.isPresent()) {
                    category.get().getPosts().add(post.get());
                    categoryRepository.save(category.get());
                } else {
                    Category newCategory = new Category();
                    newCategory.setCategory(postRequestDto.getCategory());
                    newCategory.getPosts().add(post.get());
                    categoryRepository.save(newCategory);
                }

                Map<String, Long> response = new HashMap<>();
                response.put("수정된 게시글 번호", post.get().getId());

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new CustomException(ErrCode.NOT_FOUND_POST, HttpStatus.NOT_FOUND);
            }
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_MEMBER, HttpStatus.FORBIDDEN);
        }
    }




    @Transactional
    public ResponseEntity<Void> deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            log.info("삭제됨");
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new CustomException(ErrCode.NOT_FOUND_POST, HttpStatus.NOT_FOUND);
        }
    }


    public void SaveAndReadPostWriter() {
        Member member = new Member();
        member.setStudentId("2019102236");
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

        log.info("saved post1 info : " + postRepository.findById(post1.getId()).get());
        log.info("saved post2 info : " + postRepository.findById(post2.getId()).get());
        log.info("writer info : " + postRepository.findById(post1.getId()).get().getMember().toString());

    }


}
