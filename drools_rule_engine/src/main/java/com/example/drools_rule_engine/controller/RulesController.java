package com.example.drools_rule_engine.controller;

import com.alibaba.fastjson.JSON;
import com.example.drools_rule_engine.mapper.RuleDao;
import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.Person;
import com.example.drools_rule_engine.service.RuleEngineService;
import com.example.drools_rule_engine.service.RuleStaticEngineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.Removal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/rules")
@RestController
@Slf4j
public class RulesController {
    @Autowired
    private RuleStaticEngineService ruleStaticEngineService;
    @Autowired
    private RuleEngineService ruleEngineService;
    @Autowired
    private RuleDao rulesDao;

    @RequestMapping(value = "/person/excute", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Person personRule(@RequestBody Person person) {
        return ruleEngineService.excutePersonRule(person);
    }

    @RequestMapping(value = "/meeting/excute", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MeetingVO meetingRule(@RequestBody MeetingVO meetingVO) {
        return ruleEngineService.excuteMeetingRule(meetingVO);
    }

    @RequestMapping("/person/static")
    public Person personSaticRule(@RequestBody Person person){
        log.info("======person======={}",JSON.toJSONString(person));
        return ruleStaticEngineService.excutePersonRule(person);
    }

    @RequestMapping("/meeting/static")
    public MeetingVO meetingStaticRule(@RequestBody MeetingVO meetingVO){
        log.info("======meeting============={}",JSON.toJSONString(meetingVO));
        return ruleStaticEngineService.excuteMeetingRule(meetingVO);
    }
}
