package com.example.drools_rule_engine.mapper;

import com.example.drools_rule_engine.model.MyRule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.tomcat.util.digester.Rules;

import java.util.List;

public interface RuleDao {


    @Select("SELECT id,rule_name as ruleName,rule_content as ruleContent,rule_type as ruleType,rule_salience as ruleSalience,create_time as createTime,update_time as updateTime FROM rule_drools " +
            "where rule_type = #{ruleType} order by rule_salience desc")
    List<MyRule> getRuleByType(@Param("ruleType") String ruleType);

    @Insert("INSERT INTO rule_drools(rule_name,rule_content,rule_type,rule_salience) VALUE(#{ruleName},#{ruleContent},#{ruleType},#{ruleSalience})")
    Integer addRule(MyRule myRule);

    @Select("SELECT id,rule_name as ruleName,rule_content as ruleContent,rule_type as ruleType,rule_salience as ruleSalience,create_time as createTime,update_time as updateTime " +
            "FROM rule_drools order by rule_type ,create_time DESC")
    List<MyRule> getRuleList();

    @Update("UPDATE drools_rule SET visible=0 WHERE id = #{id}")
    Integer deleteRule(@Param("id") Integer id);

    @Update("UPDATE rule_drools SET rule_name= #{ruleName} AND rule_content = #{ruleContent} and rule_type = #{ruleType} and rule_salience = #{ruleSalience} WHERE id = #{id}")
    Integer updateRule(MyRule myRule);
}

