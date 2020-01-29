package com.example.drools_rule_engine.model;

import lombok.Data;

import java.util.Date;
@Data
public class MyRule {
    /**
     * 规则的id
     * */
    private Integer id;

    /**
     * 规则的名字
     * */
    private String ruleName;

    /**
     * 规则的内容
     * */
    private String ruleContent;

    /**
     * 规则的使用对象
     * */
    private String ruleType;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 修改时间
     * */
    private Date updateTime;

    /**
     * 规则的权限
     * */
    private int ruleSalience;
}
