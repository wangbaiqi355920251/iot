package io.iti.modules.iot.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.iti.modules.iot.sys.entity.SysUserEntity;
import io.iti.modules.iot.sys.form.loginForm;
import io.iti.common.utils.R;


public interface SysUserService extends IService<SysUserEntity> {

    R UserLogin(loginForm loginForm);

    R UserRegistByInviteCode(SysUserEntity userEntity);

    boolean UserUpdate(SysUserEntity userEntity);




}
