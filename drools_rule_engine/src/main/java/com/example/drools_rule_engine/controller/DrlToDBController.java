package com.example.drools_rule_engine.controller;

import com.example.drools_rule_engine.mapper.RuleDao;
import com.example.drools_rule_engine.model.*;
import com.example.drools_rule_engine.service.RuleEngineService;
import com.example.drools_rule_engine.util.RuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/rule")
@RestController
public class DrlToDBController {
    @Autowired
    private RuleEngineService ruleEngineService;
    @Autowired
    private RuleDao rulesDao;

    private String getRuleFromFile(InputStream inputStream) {
        return ruleEngineService.handleInputStreamToString(inputStream);
    }

    /**
     * 个人展示模块的规则验证
     */
    @RequestMapping(value = "/person/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Person personRuleVerify(MultipartFile file, @RequestBody Person person) {
        String rule = null;
        try {
            InputStream inputStream = file.getInputStream();
            rule = getRuleFromFile(inputStream);
        } catch (IOException e) {
            return null;
        }

        ArrayList<Object> list = new ArrayList<>();
        list.add(person);
        RuleUtil.fireRuleAndDestroy(list, rule);
        return person;
    }


    /**
     * 会议的规则验证
     */
    @RequestMapping(value = "/meeting/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MeetingVO meetingRuleVerify(MultipartFile file, @RequestBody MeetingVO meetingVO) {
        String rule = null;
        try {
            InputStream inputStream = file.getInputStream();
            rule = getRuleFromFile(inputStream);
        } catch (IOException e) {
            return null;
        }
        ArrayList<Object> list = new ArrayList<>();
        list.add(meetingVO);
        RuleUtil.fireRuleAndDestroy(list, rule);
        return meetingVO;
    }

    /**
     * 添加规则
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO ruleAdd(MultipartFile file, @RequestBody MyRule myRule) {
        String rule = null;
        try {
            InputStream inputStream = file.getInputStream();
            rule = getRuleFromFile(inputStream);
        } catch (IOException e) {
            return null;
        }
        myRule.setRuleContent(rule);
        Integer row = rulesDao.addRule(myRule);
        if (row > 0) {
            return ResponseVO.isOk(row);
        }
        return ResponseVO.isFail();
    }

    /**
     * 展示存储的规则
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO getRuleList() {
        List<MyRule> ruleList = rulesDao.getRuleList();
        return ResponseVO.isOk(ruleList);
    }

    /**
     * 更新规则
     */
    @RequestMapping(value = "/ruleUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO ruleUpdate(MultipartFile file, @RequestBody MyRule myRule) {
        String rule = null;
        try {
            InputStream inputStream = file.getInputStream();
            rule = getRuleFromFile(inputStream);
        } catch (IOException e) {
            return null;
        }
        myRule.setRuleContent(rule);
        Integer integer = rulesDao.updateRule(myRule);
        return ResponseVO.isOk(integer);
    }

    /**
     * 删除规则
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseVO delete(@RequestParam Integer id) {
        Integer row = rulesDao.deleteRule(id);
        return ResponseVO.isOk(row);
    }
}
