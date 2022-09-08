package io.iti.service;

import io.iti.modules.iot.product.entity.RuleEntity;
import io.iti.modules.iot.product.service.RuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RuleServiceTest {
    @Autowired
    private RuleService ruleService;


    @Test
    public void insertRule(){
        String pattern = "[^\\p{ASCII}]+|(!)|(\\$)";
        RuleEntity ruleEntity = new RuleEntity();
//        ruleEntity.setRuleId((long) 1);
        ruleEntity.setRuleName("规则1");
        ruleEntity.setRegexBody(pattern);
        ruleEntity.setEncode("十六进制");
        ruleEntity.setCreateTime(new Date());
        List list = new ArrayList();
        list.add("D1");
        list.add("D2");
        list.add("温度");
//        ruleEntity.setField(list);
        ruleService.save(ruleEntity);

        /*for (Iterator<String> it = list.iterator(); it.hasNext();) {

            ruleDao.insert(it);
        }*/

    }
    @Test
    public void insertfield(){
        RuleEntity messageRule = new RuleEntity();
        messageRule.setCreateTime(new Date());
        messageRule.setEncode("十六进制");
        messageRule.setRegexBody("[^\\p{ASCII}]+|(!)|(\\$)");
        messageRule.setRuleName("长沙金码雨量计**");
//        ruleService.insertField("温度","1004");
        ruleService.save(messageRule);

    }
    @Test
    public void MsgAnalysis(){
        int id = 48;
        String msg = "24 38 30 E8 34 36 31 38 30 30 32 39 9E 36 35 32 9D 34 35 E9 30 B0 21";
        if(msg.contains(" ")) {

            msg.replace(" ", "");
        }
        System.out.println(msg);

    }
}
