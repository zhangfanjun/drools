package com.example.cooperation.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MeetingVO implements Serializable {
    private String title;
    private boolean totalShow;
    private boolean expireShow;
    private boolean joinShow;
    private int totalNumber;
    private int expireNumber;
    private int joinNumber;
}
