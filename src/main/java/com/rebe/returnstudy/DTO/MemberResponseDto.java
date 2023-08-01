package com.rebe.returnstudy.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberResponseDto {
    private Long id;
    private Integer studentId;
    private String name;
    private String generation;
    private String club;
}
