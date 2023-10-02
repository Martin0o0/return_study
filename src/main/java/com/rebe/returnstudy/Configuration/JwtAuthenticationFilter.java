package com.rebe.returnstudy.Configuration;

import com.rebe.returnstudy.Service.JwtService;
import com.rebe.returnstudy.Service.MemberService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    private final MemberService memberService;


    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {
        //Login 요청이라면 필터체인 패스
        if (request.getRequestURI().equals("/return/login")) {
            filterChain.doFilter(request, response); // "/return/login" 요청이 들어오면,
            return; //JwtAuthenticationFilter 무시
        }


        // 예외 처리 : 들어온 토큰 값이 올바르지 않은 경우 다른 체인으로 넘어감.
        final Optional<String> jwt = jwtService.extractAccessToken(request);

        // 재인증하지 않기 위해 사용자가 인증되었는지 확인.
        // 로그인은 되어있고 아직 인증은 안된 경우만 if문 내부 접근
        log.info("token 값 유효성 체크 시작 토큰 : " + jwt);
        if (jwt.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null
                && jwtService.validateToken(jwt.get())) {
            String studentId = jwtService.extractStudentId(jwt.get());
            UserDetails userDetails = memberService.loadUserByUsername(studentId);
            Authentication authentication = jwtService.getAuthentication(userDetails); //Authentication 객체 생성

            //SecurityContextHolder에 새로운 SecurityContext 영역을 할당하고,
            // 생성한 Authentication 객체를 담는다.
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

        }

        filterChain.doFilter(request, response);
    }
}

