package com.example.drools_rule_engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {

    /**
     * 异常编码，500系统异常，400没有权限
     * */
    private int code;
    private String msg;
}
