package com.rebe.returnstudy.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String studentId;
}
