package com.example.drools_rule_engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {
    private int code;
    private String msg;
    private T content;

    public static <T> ResponseVO isOk(T content) {
        ResponseVO<T> responseVO = new ResponseVO<>(200, "OK", content);
        return responseVO;
    }

    public static ResponseVO isFail() {
        return new ResponseVO<>(400, "系统异常", "");
    }
}
