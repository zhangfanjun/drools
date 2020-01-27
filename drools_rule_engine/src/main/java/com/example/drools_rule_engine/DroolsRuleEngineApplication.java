package com.example.drools_rule_engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.drools_rule_engine.mapper")
public class DroolsRuleEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroolsRuleEngineApplication.class, args);
    }

}
