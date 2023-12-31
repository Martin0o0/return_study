package com.rebe.returnstudy.Exception;


import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrCode {
    NOT_FOUND_MEMBER( "유저정보를 찾을 수 없습니다.",HttpStatus.NOT_FOUND.value()),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST.value()),
    NOT_FOUND_POST("게시글 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value());

    private final String message;
    private final int status;
}
