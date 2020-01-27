package com.example.drools_rule_engine.model;

import lombok.Data;

@Data
public class RuleParam<T> {
    private String rule;
    private T content;
}
