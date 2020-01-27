package com.example.cooperation.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cooperation.model.Person;
import com.example.cooperation.model.vo.MeetingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
@Slf4j
public class PostUtil {
    private static RestTemplate restTemplate;
    private static HttpHeaders httpHeaders;

    static {
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
    }

    public static Person cooperationStaticRule(Person person) {
        String result = postHttp("http://localhost:8080/rules/person/static",JSON.toJSONString(person));
        log.info("=======访问person规则获得的结果:{}==========",result);
        Person personVO = JSON.parseObject(result, Person.class);
        log.info("===========解析person得到的结果：{}===========",personVO);
        return personVO;
    }

    public static MeetingVO meetingStaticRule(MeetingVO meetingVO) {
        String result = postHttp("http://localhost:8080/rules/meeting/static",JSON.toJSONString(meetingVO));
        log.info("=======访问meeting规则获得的结果:{}==========",result);
        MeetingVO meeting = JSONObject.parseObject(result, MeetingVO.class);
        log.info("=======解析meeting得到的结果:{}==========",meeting);
        return meeting;
    }

    public static Person cooperationRule(Person person) {
        String result = postHttp("http://localhost:8080/rules/person/excute",JSON.toJSONString(person));
        log.info("=======访问person规则获得的结果:{}==========",result);
        Person personVO = JSON.parseObject(result, Person.class);
        log.info("===========解析person得到的结果：{}===========",personVO);
        return personVO;
    }

    public static MeetingVO meetingRule(MeetingVO meetingVO) {
        String result = postHttp("http://localhost:8080/rules/meeting/excute",JSON.toJSONString(meetingVO));
        log.info("=======访问meeting规则获得的结果:{}==========",result);
        MeetingVO meeting = JSONObject.parseObject(result, MeetingVO.class);
        log.info("=======解析meeting得到的结果:{}==========",meeting);
        return meeting;
    }

    private static String postHttp(String url,String body) {
        if ((!StringUtils.isEmpty(body)) && (!StringUtils.isEmpty(url))) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);

            String result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
//            restTemplate.postForObject(urlDown,entity,null);
            return result;
        }
        return null;

    }
}
