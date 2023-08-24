package com.rebe.returnstudy.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String category;
    @NotBlank
    private String studentId;
}
