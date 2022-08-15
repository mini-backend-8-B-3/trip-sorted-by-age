package com.hanghae.minipj.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T>  {
    private boolean success;
    private T data;
    private Error error;

    public static <T> ResponseDto<T> success(T data){
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(String code, String error){
        return new ResponseDto<>(false,null, new Error(code,error));
    }

    @Getter
    @AllArgsConstructor
    static class Error{
        private String code;
        private String message;
    }
}

