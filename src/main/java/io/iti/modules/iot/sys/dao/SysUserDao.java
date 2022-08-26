package io.iti.modules.iot.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.iti.modules.iot.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDao  extends BaseMapper<SysUserEntity> { //继承BaseMapper时加上<T>泛型，可以快捷调用简单的CRUD对于T这个表


    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    SysUserEntity selectUserByName(@Param("user_name") String username);

    /**
     * 根据username更新存活时间
     * @param username
     */

    void updateactivetimeByusername(@Param("user_name") String username);



    int isExistTheSameEmail(@Param("userId") Long userId, @Param("email") String email);

    int isExistTheSameMobile(@Param("userId") Long userId, @Param("mobile") String mobile);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);





}
