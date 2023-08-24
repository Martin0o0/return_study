package com.rebe.returnstudy.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberResponseDto {
    private Long id;
    private String studentId;
    private String name;
    private String generation;
    private String club;
    private String email;
    private String phoneNumber;
    private String statusMsg;
    private Boolean isActive;
}
