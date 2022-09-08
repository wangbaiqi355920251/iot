package io.iti.modules.iot.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_field")
public class FieldEntity {

    /**
     * 报文字段说明Id
     */
    @TableId
    private Long fieldId;

    private Integer fieldNum;

    private String fieldName;

    private String fieldDescription;

    private Long ruleId;
}
