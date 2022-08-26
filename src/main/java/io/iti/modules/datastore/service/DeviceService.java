package io.iti.modules.datastore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.iti.modules.datastore.entity.DeviceEntity;

/**
 * @param
 * @author hele
 * @time 2022-08-03  14:49
 * @return
 */
public interface DeviceService extends IService<DeviceEntity> {

    /**
     * 是否存在相同设备
     * @param device_id
     * @return
     */
    boolean isExistTheSameDevice(String device_id);

    /**
     * 插入一条设备数据记录
     */
    void saveDevice(DeviceEntity device);


}
