package io.iti.modules.iot.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.iti.common.utils.Constant;
import io.iti.common.utils.PageUtils;
import io.iti.common.utils.R;
import io.iti.modules.iot.product.entity.FieldEntity;
import io.iti.modules.iot.product.entity.RuleEntity;
import io.iti.modules.iot.product.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("iot/product")
public class ProductController {
    @Autowired
    private RuleService ruleService;

    /**
     * 插入报文解析规则
     */
    @RequestMapping(value = "/saveMessageRule",method = RequestMethod.POST)
    public R saveMessageRule(@RequestBody RuleEntity messageRule) {
//        Map<String, String> map = messageRule.getField();
        List<FieldEntity> fieldEntityList = messageRule.getField();
        List<String> list = new ArrayList<>();
        messageRule.setCreateTime(new Date());
        if (ruleService.isExistTheSameRuleName(messageRule.getRuleName())) {
            return R.error("添加报文解析规则失败，已存在该规则名称");
        }
        ruleService.save(messageRule);
//        list.add("deviceId");
//        ruleService.saveBatch(fieldEntityList);
        for(FieldEntity fieldEntity : fieldEntityList){

            list.add(fieldEntity.getFieldName());

            ruleService.insertField(fieldEntity.getFieldNum(),fieldEntity.getFieldName(),
                    fieldEntity.getFieldDescription(),messageRule.getRuleId());
        }
        //判断表是否存在

        if (ruleService.isExistTheSameTableName("tb_msg_" + messageRule.getProductId())) {
            return R.error("创建报文信息表失败，已存在该表");
        }else{
            //动态创建表
            ruleService.creatMsgTable("tb_msg_"+ messageRule.getProductId(),list);

        }

        return R.ok();
    }

    /**
     * 设备数据查询
     */
    @RequestMapping(value = "/getDeviceMsg",method = RequestMethod.GET)
    public R getDeviceMsg(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        System.out.println(params.get("page"));
        long curPage = Long.parseLong((String)params.get(Constant.PAGE));
        long limit = Long.parseLong((String)params.get(Constant.LIMIT));
        long productId = Long.parseLong((String)params.get("productId"));
        String deviceId = (String)params.get("deviceId");
        long ruleId = Long.parseLong((String)params.get("ruleId"));
        //根据规则Id查询字段说明
        List<Map<String,Object>> fieldList = ruleService.queryFieldName(ruleId);
        System.out.println(fieldList);
        //查询设备发送的信息，并进行分页展示
        PageUtils page = ruleService.queryDeviceMsgPage(curPage,limit,deviceId, productId);

        R r = R.ok().put("page", page);
        r.put("field", fieldList);
        return r;
    }



}
