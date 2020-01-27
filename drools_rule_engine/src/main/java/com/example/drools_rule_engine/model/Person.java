package com.example.drools_rule_engine.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.util.NotImplemented;

@Data
@NoArgsConstructor
public class Person {
    private String account;
    private String name;
    private int grade;
    private Boolean meetingShow;
    private Boolean taskShow;

    public Person(String account, String name, int grade){
        this.account=account;
        this.name=name;
        this.grade=grade;
    }
}
