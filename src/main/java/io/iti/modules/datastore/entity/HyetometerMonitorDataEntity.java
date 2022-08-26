package io.iti.modules.datastore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @param
 * @author hele
 * @time 2022-08-03  8:49
 * @return
 */
@Data
@TableName("south_survey_hyetometer_sensor_data")
public class HyetometerMonitorDataEntity implements Serializable {
    public static final long SerialVersionUID=1L;

    /**
     * 自增主键
     */
    @TableId
    private Integer id;

    /**
     * 传感器名称
     */
    private String sensorName;

    /**
     * 一次数据上报间隔内的降雨量(单位：mm)
     */
    private Float value;

    /**
     * 当日雨量累积值(单位：mm)
     */
    private Float totalValue;

    /**
     * 上传数据的时刻
     */
    private String date;

    /**
     * 所属设备id
     */
    private String device_id;


}
