package io.iti.modules.datastore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.iti.common.utils.R;
import io.iti.modules.datastore.entity.HyetometerMonitorDataEntity;
import io.iti.modules.datastore.validate.GNSSDeviceInfoValidateEntity;
import io.iti.modules.datastore.validate.HyetometerDeviceInfoValidateEntity;

import java.util.Map;

/**
 * @param
 * @author hele
 * @time 2022-08-04  14:29
 * @return
 */
public interface HyetometerMonitorDataService extends IService<HyetometerMonitorDataEntity> {

    /**
     * 保存雨量计传感器数据
     */
    R saveData(String deviceId, Map<String, Object> params, HyetometerDeviceInfoValidateEntity deviceInfo, String key);


}
