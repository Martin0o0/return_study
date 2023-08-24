package com.rebe.returnstudy.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberRequestDto {
    @NotBlank
    private String studentId;
    @NotBlank
    @Size(min = 2, max = 4)
    private String name;
    @NotBlank
    private String generation;
    @NotBlank
    private String club;
    //chy0310@khu.ac.kr처럼 @khu.ac.kr의 도메인 이메일 주소만 허용
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@khu\\.ac\\.kr$")
    private String email;

    //010XXXXXXXX만 허용
    @Pattern(regexp = "^010\\d{8}$")
    private String phoneNumber;

    @Size(min = 0, max = 30)
    private String statusMsg;
}
