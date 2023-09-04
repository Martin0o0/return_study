package com.rebe.returnstudy.DTO;

import com.rebe.returnstudy.Exception.ErrCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponseDto {

    private int status;

    private Object message;

    public ExceptionResponseDto(ErrCode errCode) {
        this.message = errCode.getMessage();
        this.status = errCode.getStatus();
    }

    public ExceptionResponseDto(int status, HashMap<String, String> message){
        this.status = status;
        this.message = message;
    }

}
