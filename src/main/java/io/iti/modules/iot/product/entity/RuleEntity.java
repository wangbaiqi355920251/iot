package io.iti.modules.iot.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@TableName("tb_rule")
public class RuleEntity implements Serializable {
    /**
     * 报文解析Id
     */
    @TableId
    private Long ruleId;
    /**
     *
     * 报文解析规则名称
     */
    private String ruleName;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 报文头部解析规则
     */
    private String regexHeader;
    /**
     * 报文体解析规则
     */
    private String regexBody;
    /**
     * 报文头部字段数量
     */
    private Integer fieldNumHead;
    /**
     * 单条报文信息字段数量
     */
    private Integer fieldNumBody;
    /**
     * 报文起始符
     */
    private String startSymbol;
    /**
     * 报文终止符
     */
    private String endSymbol;
    /**
     * 报文编码类型
     */
    private String encode;
    /**
     * 报文解码类型
     */
    private String decode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 字段说明
     */
    @TableField(exist = false)
//    private Map<String,String> field;
    private List<FieldEntity> field;




}
