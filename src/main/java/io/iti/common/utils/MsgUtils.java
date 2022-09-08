package io.iti.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.iti.modules.iot.product.entity.RuleEntity;
import io.iti.modules.iot.product.service.RuleService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MsgUtils {

    public static void MsgAnalysis(String Msg,RuleService ruleService) {
        //1.拿到发送过来的报文信息，从数据库中获取报文开始标识和结束标识，去掉开始标识和结束标识

        //2.从数据库中的规则表中获取报文解析规则、报文头部字段数，单条报文数据字段数，分别截取报文头部和报文体信息（其中报文体信息
        // 包含多条报文数据，将多条报文数据存入List<String>中）

        //3.按照报文解析规则分别解析报文头部和报文体信息，并将其存入数据库中
        System.out.println(Msg);
        if(Msg.contains(" ")) {
            Msg = Msg.replace(" ", "");
        }
        //将16进制的报文转为Ascii
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < Msg.length(); i += 2) {
            String str = Msg.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        //从数据库中查找规则
        QueryWrapper<RuleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",48);
        RuleEntity ruleEntity = ruleService.getOne(wrapper);
        System.out.println(ruleEntity.getRegexBody());

        //使用规则解析报文
        String s = output.substring(1,output.length()-1);//报文去头去尾
        Pattern splitter = Pattern.compile(ruleEntity.getRegexBody());
//        Pattern splitter = Pattern.compile("[^\\p{ASCII}]+|(!)|(\\$)");
        String[] result = splitter.split(s);
        List<String> list = Stream.of(result).collect(Collectors.toList());
        System.out.println(list);
        //将解析出来的信息插入表中
        List<String> colName = ruleService.selectTableColName(1);
        colName.add("create_date");
        System.out.println(colName);
//        colName = colName.subList(1, colName.size() - 2);
//        System.out.println(colName);
        Date nowdate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        list.add(Timestamp.valueOf(simpleDate.format(nowdate)).toString());
        System.out.println(list);
        ruleService.insertMsg("tb_msg_48",list,colName);
        System.out.println(colName);
    }
}
