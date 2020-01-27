package com.example.drools_rule_engine.service.impl;

import com.example.drools_rule_engine.mapper.RuleDao;
import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.MyRule;
import com.example.drools_rule_engine.model.Person;
import com.example.drools_rule_engine.service.RuleEngineService;
import com.example.drools_rule_engine.util.RuleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.Rules;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RuleEngineServiceImpl implements RuleEngineService {

    @Autowired
    private RuleDao rulesDao;

    @Override
    public Person excutePersonRule(Person person) {
//        获取人员展示模块的规则，获取的规则按照权重反序
        List<MyRule> ruleList = rulesDao.getRuleByType("person");
        if(CollectionUtils.isEmpty(ruleList)){
            return person;
        }
        String rule;
        for (MyRule myRule : ruleList) {
            rule = myRule.getRuleContent();
            ArrayList<Object> list = new ArrayList<>();
            list.add(person);
            RuleUtil.fireRule(list,rule);
        }

        return person;
    }

    @Override
    public MeetingVO excuteMeetingRule(MeetingVO meetingVO) {
        //        获取人员展示模块的规则
        List<MyRule> ruleList = rulesDao.getRuleByType("meeting");
        if(CollectionUtils.isEmpty(ruleList)){
            return meetingVO;
        }
        String rule;
        for (MyRule myRule : ruleList) {
            rule = myRule.getRuleContent();
            ArrayList<Object> list = new ArrayList<>();
            list.add(meetingVO);
            RuleUtil.fireRule(list,rule);
        }

        return meetingVO;
    }

    @Override
    public String getRuleTable(InputStream inputStream) {
        //把excel翻译成drl文件
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String rules = compiler.compile(ResourceFactory.newInputStreamResource(inputStream, "UTF-8"), "rule-table");
        log.info("get rule from xls:" + rules);
        return rules;
    }
}
