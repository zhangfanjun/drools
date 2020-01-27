package com.example.drools_rule_engine.controller;

import com.alibaba.fastjson.JSON;
import com.example.drools_rule_engine.mapper.RuleDao;
import com.example.drools_rule_engine.model.MeetingVO;
import com.example.drools_rule_engine.model.MyRule;
import com.example.drools_rule_engine.model.Person;
import com.example.drools_rule_engine.model.ResponseVO;
import com.example.drools_rule_engine.service.RuleEngineService;
import com.example.drools_rule_engine.service.RuleStaticEngineService;
import com.example.drools_rule_engine.util.RuleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/rules")
@RestController
@Slf4j
public class RulesManagerController {


    @Autowired
    private RuleStaticEngineService ruleStaticEngineService;
    @Autowired
    private RuleEngineService ruleEngineService;
    @Autowired
    private RuleDao rulesDao;

    /**
     * 规则的格式转换和验证
     * */
    @RequestMapping(value = "/getRuleXls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getRuleXls(@RequestParam(value = "file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String rule = ruleEngineService.getRuleTable(inputStream);
//        try {
//            KieSession kieSession = rulesService.getKieSession(rule);
//            kieSession.insert(new Person());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new JsonResponse(e);
//        }
        return null;
    }

    /**
     * 个人展示模块的规则验证
     * */
    @RequestMapping(value = "/person/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String personRuleVerify(@RequestParam(value = "rule") String rule,
                             @RequestBody Person person) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(person);
        RuleUtil.fireRuleAndDestroy(list,rule);
        return JSON.toJSONString(person);
    }

    /**
     * 会议的规则验证
     * */
    @RequestMapping(value = "/meeting/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String meetingRuleVerify(@RequestParam(value = "rule") String rule,
                                   @RequestBody MeetingVO meetingVO) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(meetingVO);
        RuleUtil.fireRuleAndDestroy(list,rule);
        return JSON.toJSONString(meetingVO);
    }

    /**
     * 添加规则
     * */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO ruleAdd(@RequestBody MyRule myRule) {
        Integer row = rulesDao.addRule(myRule);
        if(row>0){
            return ResponseVO.isOk(row);
        }
        return ResponseVO.isFail();
    }

    /**
     * 展示存储的规则
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO getRuleList() {
        List<MyRule> ruleList = rulesDao.getRuleList();
        return ResponseVO.isOk(ruleList);
    }

    /**
     * 更新规则
     * */
    @RequestMapping(value = "/ruleUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO ruleUpdate(@RequestBody MyRule myRule) {
        Integer integer = rulesDao.updateRule(myRule);
        return ResponseVO.isOk(integer);
    }

    /**
     * 删除规则
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO delete(@RequestParam Integer id) {
        Integer row = rulesDao.deleteRule(id);
        return ResponseVO.isOk(row);
    }

    private final static String verify="规则：package com.xu.drools;\n" +
            "import com.xu.drools.bean.Person;\n" +
            "rule \"2\"\n" +
            "\twhen\n" +
            "        $p : Person(age < 30);\n" +
            "    then\n" +
            "\t\tSystem.out.println(\"hello, young xu2!\");\n" +
            "\t\t$p.setDesc(\"young \"+$p.getName());\n" +
            "\t\tretract($p)\n" +
            "end"+"   数据实体：{\n" +
            "    \"age\":18,\n" +
            "    \"name\":\"xu\",\n" +
            "    \"desc\":\"帅\"\n" +
            "}";
}
