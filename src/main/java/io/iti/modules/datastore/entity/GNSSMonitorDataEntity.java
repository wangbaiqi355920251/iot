package io.iti.modules.datastore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @param
 * @author hele
 * @time 2022-07-29  16:24
 * @return
 */
@Data
@TableName("south_survey_gnss_sensor_data")
public class GNSSMonitorDataEntity implements Serializable {
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
     * GNSS监测点初始位置差值，X向位移(单位：mm)
     */
    private Float gpsTotalX;

    /**
     * GNSS监测点初始位置差值，Y向位移(单位：mm)
     */
    private Float gpsTotalY;

    /**
     * GNSS监测点初始位置差值，Z向位移(单位：mm)
     */
    private Float gpsTotalZ;

    /**
     * 上传数据的时刻
     */
    private String date;

    /**
     * 所属设备id
     */
    private String device_id;

}
