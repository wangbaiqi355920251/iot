package io.iti.modules.datastore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.iti.common.utils.R;
import io.iti.modules.datastore.dao.GNSSMonitorDataDao;
import io.iti.modules.datastore.entity.DeviceEntity;
import io.iti.modules.datastore.service.DeviceService;
import io.iti.modules.datastore.validate.GNSSDeviceInfoValidateEntity;
import io.iti.modules.datastore.entity.GNSSMonitorDataEntity;
import io.iti.modules.datastore.validate.GNSSSensorValidateEntity;
import io.iti.modules.datastore.service.GNSSMonitorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @param
 * @author hele
 * @time 2022-08-03  10:34
 * @return
 */
@Service("GNSSMonitorDataService")
public class GNSSMonitorDataServiceImpl extends ServiceImpl<GNSSMonitorDataDao, GNSSMonitorDataEntity> implements GNSSMonitorDataService {

    @Autowired
    private DeviceService deviceService;


    @Override
    public R saveData(String deviceId, Map<String, Object> params, GNSSDeviceInfoValidateEntity deviceInfo, String key) {

        if(!key.equals("5206d0a59ca333d6d6b64d6d9a6d85b1f04fdc679e005af58be929aefb0837b0")) {
            return R.error("鉴权失败，请输入正确的key");
        }

        String type = (String) params.get("type");
        String deviceName = deviceInfo.getDevice_name();
        String deviceSn = deviceInfo.getDevice_sn();
        List<GNSSSensorValidateEntity> sensors = deviceInfo.getSensor();
        String date = deviceInfo.getDate();
        String des = deviceInfo.getDesc();

        if(!deviceService.isExistTheSameDevice(deviceId)) {
            DeviceEntity device = new DeviceEntity();
            device.setDeviceId(deviceId);
            device.setDeviceName(deviceName);
            device.setDeviceSn(deviceSn);
            device.setDes(des);
            deviceService.saveDevice(device);
        }

        if(type.equals("1")) {
            int nums = baseMapper.insertBatchType1(deviceId, sensors, date);
            if(nums>0) {
                return R.ok();
            }
            return R.error("数据点格式类型一保存数据失败");
        }
        return R.error("请输入的正确的type");
    }

}
