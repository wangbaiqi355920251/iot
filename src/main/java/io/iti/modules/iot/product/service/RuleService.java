package io.iti.modules.iot.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.iti.common.utils.PageUtils;
import io.iti.modules.iot.product.entity.RuleEntity;

import java.util.List;
import java.util.Map;

public interface RuleService extends IService<RuleEntity> {
    //插入报文规则字段
    void insertField(Integer field_num, String field_name, String field_description, Long rule_id);
    //动态创建报文信息存储表
    void creatMsgTable(String tableName, List<String> cloums);
    //获取表的字段名
    List<String> selectTableColName(Integer ruleId);
    //将设备报文信息存入表
    void insertMsg(String tableName,List<String> Msg,List<String> colName);
    //查询报文信息表的信息
    PageUtils queryDeviceMsgPage (Long page, Long size, String deviceId, Long productId);
    //根据报文解析规则Id查询报文字段说明
    List<Map<String,Object>> queryFieldName (Long ruleId);
    //判断是否存在该报文规则
    boolean isExistTheSameRuleName(String ruleName);
    //判断是否存在存储产品报文信息的表
    boolean isExistTheSameTableName(String tableName);

}
