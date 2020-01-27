package com.example.cooperation.controller;

import com.alibaba.fastjson.JSON;
import com.example.cooperation.model.Person;
import com.example.cooperation.model.vo.CooperationVO;
import com.example.cooperation.model.vo.MeetingVO;
import com.example.cooperation.model.vo.ResponseVO;
import com.example.cooperation.model.vo.TaskVO;
import com.example.cooperation.util.PostUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

@RestController
@Slf4j
@RequestMapping("/cooperation")
public class CooperationController {

    @RequestMapping("/getInfo/static")
    public ResponseVO getByStaticRule(@RequestHeader("account") String account){
//        获取个人的信息，这里直接new一个信息
        Person xiaoming = new Person(account, "xiaoming", 3);
//        请求规则，进行展示模块的判断
        Person personVO = PostUtil.cooperationStaticRule(xiaoming);

        log.info("===xiaoming=={}", JSON.toJSONString(xiaoming));
        MeetingVO meetingVO = new MeetingVO();
        TaskVO taskVO = new TaskVO();

        MeetingVO meeting =null;
//        根据规则进行针对性的请求数据
        if(personVO.getMeetingShow()){
//            获取会议数据，这里直接new一个会议数据
            meetingVO.setTotalNumber(99);
            meetingVO.setExpireNumber(33);
            meetingVO.setJoinNumber(66);
//            请求规则，对数据的展示进行筛选
            meeting = PostUtil.meetingStaticRule(meetingVO);
        }
        if(personVO.getTaskShow()){
//            获取任务数据
//            请求规则
        }

        return ResponseVO.isOK(new CooperationVO(meeting,taskVO));
    }

    @RequestMapping("/getInfo")
    public ResponseVO getInfo(@RequestHeader("account") String account){
//        获取个人的信息，这里直接new一个信息
        Person xiaoming = new Person(account, "xiaoming", 3);
//        请求规则，进行展示模块的判断
        Person personVO = PostUtil.cooperationRule(xiaoming);

        log.info("===xiaoming=={}", JSON.toJSONString(xiaoming));
        MeetingVO meetingVO = new MeetingVO();
        TaskVO taskVO = new TaskVO();

        MeetingVO meeting =null;
//        根据规则进行针对性的请求数据
        if(personVO.getMeetingShow()){
//            获取会议数据，这里直接new一个会议数据
            meetingVO.setTotalNumber(99);
            meetingVO.setExpireNumber(33);
            meetingVO.setJoinNumber(66);
//            请求规则，对数据的展示进行筛选
            meeting = PostUtil.meetingRule(meetingVO);
        }
        if(personVO.getTaskShow()){
//            获取任务数据
//            请求规则
        }

        return ResponseVO.isOK(new CooperationVO(meeting,taskVO));
    }
}
