package io.iti.modules.datastore.controller;

import io.iti.common.utils.R;
import io.iti.common.validator.ValidatorUtils;
import io.iti.modules.datastore.service.HyetometerMonitorDataService;
import io.iti.modules.datastore.validate.GNSSDeviceInfoValidateEntity;
import io.iti.modules.datastore.service.GNSSMonitorDataService;
import io.iti.modules.datastore.validate.HyetometerDeviceInfoValidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @param
 * @author Jing
 * @time 2022-07-29  15:50
 * @return
 */
@RestController
public class DataStoreController {

    @Autowired
    private GNSSMonitorDataService gnssMonitorDataService;

    @Autowired
    private HyetometerMonitorDataService hyetometerMonitorDataService;


    /**
     *  GNSS设备存储
     */
    @RequestMapping("/gnss/devices/{deviceId}/datapoints")
    public R reported(@PathVariable String deviceId, @RequestParam Map<String, Object> params,
                      @RequestBody GNSSDeviceInfoValidateEntity deviceInfo, @RequestHeader("key") String key) {

        ValidatorUtils.validateEntity(deviceInfo);
        R r = gnssMonitorDataService.saveData(deviceId, params, deviceInfo, key);
        return r;
    }


    /**
     *  雨量计设备存储
     */
    @RequestMapping("/hyetometer/devices/{deviceId}/datapoints")
    public R store(@PathVariable String deviceId, @RequestParam Map<String, Object> params,
                   @RequestBody HyetometerDeviceInfoValidateEntity deviceInfo, @RequestHeader("key") String key) {
        ValidatorUtils.validateEntity(deviceInfo);
        R r = hyetometerMonitorDataService.saveData(deviceId, params, deviceInfo, key);
        return r;
    }


}
