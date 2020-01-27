package com.example.drools_rule_engine.service;

import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.Person;

public interface RuleStaticEngineService {
    /**
     * 静态执行展示规则
     * */
    Person excutePersonRule(Person person);

    /**
     * 静态执行会议的规则
     * */
    MeetingVO excuteMeetingRule(MeetingVO meetingVO);
}
