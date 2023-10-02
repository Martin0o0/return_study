package com.rebe.returnstudy.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;


@Getter
public class CustomException extends RuntimeException{
    private final ErrCode errCode;

    private final HttpStatus httpStatus;


    public CustomException(final ErrCode errCode, final HttpStatus status) {
        super(errCode.getMessage());
        this.errCode = errCode;
        this.httpStatus = status;
    }


}
