package com.example.cooperation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseVO <T>{
    private int code;
    private String msg;
    private T content;

    public static<T> ResponseVO isOK(T content){
        ResponseVO<T> responseVO = new ResponseVO<>(200,"ok",content);
        return responseVO;
    }
}
