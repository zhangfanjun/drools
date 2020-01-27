package com.example.cooperation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Person  implements Serializable {
    private String account;
    private String name;
    private int grade;
    private Boolean meetingShow;
    private Boolean taskShow;

    public Person (String account,String name,int grade){
        this.account=account;
        this.name=name;
        this.grade=grade;
    }
}
