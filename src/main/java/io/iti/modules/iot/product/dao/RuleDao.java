package io.iti.modules.iot.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.iti.modules.iot.product.entity.RuleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RuleDao extends BaseMapper<RuleEntity> {

    /**
     * 添加报文字段解析
     */
    int insertField(@Param("field_num") Integer field_num,@Param("field_name") String field_name,@Param("field_description") String field_description, @Param("rule_id") Long rule_id);

    /**
     * 动态创建存储产品报文信息表
     */
    void creatMsgTable(@Param("tableName")String tableName, @Param("cloums") List<String> cloums);
    /**
     * 获取表的列名
     */
    List<String> selectTableColName(@Param("ruleId")Integer ruleId);
    /**
     * 插入设备报文信息
     * @param tableName
     * @param Msg
     * @return
     */
    void insertMsg(@Param("tableName")String tableName,@Param("Msg")List<String> Msg,@Param("colName")List<String> colName);

    /**
     * 查询设备报文信息
     * @param pageInfo
     * @param deviceId
     * @return
     */
    List<Map<String,Object>> queryDeviceMsgPage (@Param("pageInfo")Page pageInfo, @Param("deviceId")String deviceId, @Param("tableName")String tableName);

    /**
     * 根据规则Id查找字段说明
     * @param ruleId
     * @return
     */
    List<Map<String,Object>> queryFieldName(@Param("ruleId") Long ruleId);
    /**
     * 判断ruleName是否已存在
     * @param ruleName
     */
    int isExistTheSameRuleName(String ruleName);

    /**
     * 判断tableName是否已存在
     */
    int isExistTheSameTableName(String tableName);
}
