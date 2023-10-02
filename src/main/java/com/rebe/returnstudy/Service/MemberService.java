package com.rebe.returnstudy.Service;


import com.rebe.returnstudy.DTO.LoginRequestDto;
import com.rebe.returnstudy.DTO.LoginResponseDto;
import com.rebe.returnstudy.DTO.MemberRequestDto;
import com.rebe.returnstudy.DTO.MemberResponseDto;
import com.rebe.returnstudy.Entity.Member;
import com.rebe.returnstudy.Entity.MemberDetails;
import com.rebe.returnstudy.Entity.Post;
import com.rebe.returnstudy.Entity.Role;
import com.rebe.returnstudy.Exception.CustomException;
import com.rebe.returnstudy.Exception.ErrCode;
import com.rebe.returnstudy.Repository.MemberRepository;
import com.rebe.returnstudy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //서비스 레이어의 클래스에 사용하는 컴포넌트 시리즈 어노테이션
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;




    //Create
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {

        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setStudentId(memberRequestDto.getStudentId());
        member.setGeneration(memberRequestDto.getGeneration());
        member.setClub(memberRequestDto.getClub());

        //Role을 구별
        if(memberRequestDto.getRole().equals("ROLE_ADMIN")){
            member.setRole(Role.ROLE_ADMIN.getRole());
        }else{
            member.setRole(Role.ROLE_USER.getRole());
        }
        member.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));



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
        log.info("회원가입 성공");

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


    @Override
    public UserDetails loadUserByUsername(String username) {
       log.info("사용자 아이디 : " + username);
       return memberRepository.findByStudentId(username).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없음"));
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByStudentId(loginRequestDto.getId())
                .orElseThrow(() -> new CustomException(ErrCode.NOT_FOUND_MEMBER, HttpStatus.NOT_FOUND));
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())){
            throw new CustomException(ErrCode.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setId(member.getStudentId());
            loginResponseDto.setJWT(jwtService.generateToken(member.getStudentId(), member.getRole()));
            return new ResponseEntity<LoginResponseDto>(loginResponseDto, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getMembers() {
        List<MemberResponseDto> response = new ArrayList<>();
        List<Member> members = memberRepository.findAll();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setEmail(member.getMemberDetails().getEmail());
            memberResponseDto.setClub(member.getClub());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setPhoneNumber(member.getMemberDetails().getPhoneNumber());
            memberResponseDto.setStudentId(member.getStudentId());
            response.add(memberResponseDto);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
