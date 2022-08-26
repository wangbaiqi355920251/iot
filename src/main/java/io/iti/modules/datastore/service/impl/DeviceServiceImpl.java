package io.iti.modules.datastore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.iti.modules.datastore.dao.DeviceDao;
import io.iti.modules.datastore.entity.DeviceEntity;
import io.iti.modules.datastore.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * @param
 * @author hele
 * @time 2022-08-03  14:50
 * @return
 */
@Service("DeviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceEntity> implements DeviceService {
    @Override
    public boolean isExistTheSameDevice(String device_id) {
        if(baseMapper.selectDeviceById(device_id)!=null) {
            return true;
        }
        return false;
    }

    @Override
    public void saveDevice(DeviceEntity device) {
        baseMapper.insert(device);
    }
}
