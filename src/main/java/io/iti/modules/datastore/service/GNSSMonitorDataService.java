package io.iti.modules.datastore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.iti.common.utils.R;
import io.iti.modules.datastore.validate.GNSSDeviceInfoValidateEntity;
import io.iti.modules.datastore.entity.GNSSMonitorDataEntity;

import java.util.Map;

/**
 * @param
 * @author hele
 * @time 2022-08-03  10:30
 * @return
 */
public interface GNSSMonitorDataService extends IService<GNSSMonitorDataEntity> {

    /**
     * 保存GNSS传感器数据
     */
    R saveData(String deviceId, Map<String, Object> params, GNSSDeviceInfoValidateEntity deviceInfo, String key);



}
