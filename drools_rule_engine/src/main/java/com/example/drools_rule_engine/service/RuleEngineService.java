package com.example.drools_rule_engine.service;

import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.Person;

import java.io.InputStream;

public interface RuleEngineService {
    String getRuleTable(InputStream inputStream);
    /**
     * 人员展示模块规则执行
     * */
    Person excutePersonRule(Person person);

    /**
     * 会议模块的规则执行
     * */
    MeetingVO excuteMeetingRule(MeetingVO meetingVO);
}
