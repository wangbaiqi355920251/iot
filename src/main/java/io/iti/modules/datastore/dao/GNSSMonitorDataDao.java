package io.iti.modules.datastore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.iti.modules.datastore.entity.GNSSMonitorDataEntity;
import io.iti.modules.datastore.validate.GNSSSensorValidateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @param
 * @author hele
 * @time 2022-08-03  10:35
 * @return
 */
@Mapper
public interface GNSSMonitorDataDao extends BaseMapper<GNSSMonitorDataEntity> {


    int insertBatchType1(@Param("deviceId") String deviceId, @Param("sensors") List<GNSSSensorValidateEntity> sensors,
                        @Param("date") String date);

    int insertBatchType2(@Param("deviceId") String deviceId, @Param("sensors") List<GNSSSensorValidateEntity> sensors,
                         @Param("date") String date);


}
