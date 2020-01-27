package com.example.cooperation.model.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class TaskVO implements Serializable {
    private final static Long seriable = 1l;
    private String title;
}
