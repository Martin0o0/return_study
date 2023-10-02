package com.rebe.returnstudy.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//1
@Configuration
@EnableWebSecurity //모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션이다.
// 내부적으로 SecurityFilterChain이 동작하여 URL 필터가 적용
@RequiredArgsConstructor
public class SecurityConfiguration {

    //2
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    //3
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) //httpBasic, 즉 UI를 사용하는 것을 기본으로 하는 시큐리티 기본 설정을 disable
                .formLogin(AbstractHttpConfigurer::disable)//form 형태의 로그인 disable
                .csrf(AbstractHttpConfigurer::disable)// csrf  disable
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //h2 데이터 베이스 사용을 위해
                .authorizeHttpRequests(
                        //4
                        req -> req
                                .requestMatchers(PathRequest.toH2Console()).permitAll() //h2
                                .requestMatchers("/return/login", "/return/registration").permitAll()
                                .requestMatchers("/return/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()

                                .anyRequest().authenticated()
                        // 나머지 경로는 전부 승인 받아야함
                )
                //5
                .sessionManagement(
                        // 세션을 stateless하게 만든다.
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //6
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // JWT Token 필터를 id/password 인증 필터 이전에 추가
        return http.build();
    }


    //7
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
    }
}
