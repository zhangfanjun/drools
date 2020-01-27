package com.example.drools_rule_engine.model;

import lombok.Data;

@Data
public class MeetingVO {
    private String title;
    private boolean totalShow;
    private boolean expireShow;
    private boolean joinShow;
    private int totalNumber;
    private int expireNumber;
    private int joinNumber;
}
