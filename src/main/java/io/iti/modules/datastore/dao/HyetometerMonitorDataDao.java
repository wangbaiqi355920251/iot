package io.iti.modules.datastore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.iti.modules.datastore.entity.HyetometerMonitorDataEntity;
import io.iti.modules.datastore.validate.GNSSSensorValidateEntity;
import io.iti.modules.datastore.validate.HyetometerDataValiateType1Entity;
import io.iti.modules.datastore.validate.HyetometerDeviceInfoValidateEntity;
import io.iti.modules.datastore.validate.HyetometerSensorValidateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @param
 * @author hele
 * @time 2022-08-04  14:40
 * @return
 */
@Mapper
public interface HyetometerMonitorDataDao extends BaseMapper<HyetometerMonitorDataEntity> {

    int insertBatchType1(@Param("deviceId") String deviceId, @Param("sensors") List<HyetometerSensorValidateEntity> sensors,
                         @Param("date") String date);


}
