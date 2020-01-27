package com.example.drools_rule_engine.util;

import com.example.drools_rule_engine.MyException;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleUtil {

    public static KieSession getKieSessionByRule(String rules) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/rules.drl", rules.getBytes());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new MyException(300003,results.getMessages().toString());
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();

        return kieBase.newKieSession();
    }

    public static void fireRule(List<Object> list, String rule){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        KieSession kieSession = getKieSessionByRule(rule);
        for (Object o : list) {
            kieSession.insert(o);
        }
        kieSession.fireAllRules();
    }

    public static void fireRuleAndDestroy(ArrayList<Object> list, String rule) {
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        KieSession kieSession = getKieSessionByRule(rule);
        for (Object o : list) {
            kieSession.insert(o);
        }
        kieSession.fireAllRules();
        kieSession.destroy();
    }
}
