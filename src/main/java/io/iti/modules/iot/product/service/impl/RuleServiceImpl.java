package io.iti.modules.iot.product.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.iti.common.utils.PageUtils;
import io.iti.modules.iot.product.dao.RuleDao;
import io.iti.modules.iot.product.entity.RuleEntity;
import io.iti.modules.iot.product.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ruleService")
public class RuleServiceImpl extends ServiceImpl<RuleDao, RuleEntity> implements RuleService {

    public void insertField(Integer field_num, String field_name, String field_description,Long rule_id){
        baseMapper.insertField(field_num,field_name,field_description,rule_id);
    }
    public void creatMsgTable(String tableName, List<String> cloums){
        baseMapper.creatMsgTable(tableName, cloums);
    }

    /**
     * 获取表字段名
     */
    public List<String> selectTableColName(Integer ruleId){
       return baseMapper.selectTableColName(ruleId);
    }
    /**
     * 插入报文信息
     */
    public void insertMsg(String tableName,List<String> Msg,List<String> colName){
        baseMapper.insertMsg(tableName,Msg,colName);
    }
    /**
     * 查询设备报文信息分页
     */
    public PageUtils queryDeviceMsgPage (Long page, Long size, String deviceId, Long productId){
        String tableName = "tb_msg_" + productId;
        Page pageInfo = new Page(page,size);
        List<Map<String,Object>> pageList = baseMapper.queryDeviceMsgPage(pageInfo,deviceId,tableName);
        pageInfo.setRecords(pageList);
        return new PageUtils(pageInfo);

    }

    /**
     * 根据ruleId查询报文解析规则的字段说明
     * @param ruleId
     * @return
     */
    public List<Map<String,Object>> queryFieldName (Long ruleId){

        return baseMapper.queryFieldName (ruleId);
    }

    /**
     * 判断该规则名是否已存在
     */
    public boolean isExistTheSameRuleName(String ruleName) {
        if (baseMapper.isExistTheSameRuleName(ruleName) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断该规则名是否已存在
     */
    public boolean isExistTheSameTableName(String tableName) {
        if (baseMapper.isExistTheSameTableName(tableName) > 0) {
            return true;
        } else {
            return false;
        }
    }


}
