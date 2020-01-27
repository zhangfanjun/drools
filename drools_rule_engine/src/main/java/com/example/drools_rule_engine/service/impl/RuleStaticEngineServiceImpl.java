package com.example.drools_rule_engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.Person;
import com.example.drools_rule_engine.service.RuleStaticEngineService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class RuleStaticEngineServiceImpl implements RuleStaticEngineService {

    @Override
    public Person excutePersonRule(Person person) {
        String kessionName = "personKession";
        execute(kessionName,person);
        return person;
    }

    @Override
    public MeetingVO excuteMeetingRule(MeetingVO meetingVO) {
        String kessionName = "meetingKession";
        execute(kessionName,meetingVO);
        return meetingVO;
    }

    private void execute(String kessionName,Object object) {
        if(object==null){
            return;
        }
        log.info("kessionName:{}======静态规则的输入对象:{}",kessionName,JSON.toJSONString(object));
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession(kessionName);
        ksession.insert(object);
        ksession.fireAllRules();
        ksession.destroy();
        log.info("======静态规则的输出对象====={}",JSON.toJSONString(object));
    }
}
