package io.iti.modules.datastore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 传感器设备
 * @author hele
 * @time 2022-07-29  17:08
 * @return
 */
@Data
@TableName("south_survey_device")
public class DeviceEntity implements Serializable {
    public static final long SerialVersionUID=1L;

    /**
     * 主键自增id
     */
    @TableId
    private Integer id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备名
     */
    private String deviceName; 

    /**
     * 设备SN
     */
    private String deviceSn;

    /**
     * 设备描述
     */
    private String des;
}
