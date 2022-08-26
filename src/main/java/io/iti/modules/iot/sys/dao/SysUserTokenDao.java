package io.iti.modules.iot.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.iti.modules.iot.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserTokenDao  extends BaseMapper<SysUserTokenEntity> /*注意此处<Entity>*/{
    SysUserTokenEntity queryByToken(String token);




}
