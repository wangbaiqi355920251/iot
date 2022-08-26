package io.iti.modules.datastore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.iti.modules.datastore.entity.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @param
 * @author hele
 * @time 2022-08-03  14:52
 * @return
 */
@Mapper
public interface DeviceDao extends BaseMapper<DeviceEntity> {

    DeviceEntity selectDeviceById(@Param("device_id") String device_id);
}
