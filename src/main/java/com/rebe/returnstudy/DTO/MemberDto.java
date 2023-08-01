package com.rebe.returnstudy.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDto {

    private Integer studentId;
    private String name;
    private String generation;
    private String club;

}
