package io.iti.modules.iot.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.iti.common.exception.RRException;
import io.iti.common.utils.R;
import io.iti.modules.iot.sys.dao.SysUserDao;
import io.iti.modules.iot.sys.entity.SysUserEntity;
import io.iti.modules.iot.sys.form.loginForm;
import io.iti.modules.iot.sys.service.SysUserService;
import io.iti.modules.iot.sys.service.SysUserTokenService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("SysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public R UserLogin(loginForm loginform){

        String username = loginform.getUsername();

        String userkey = loginform.getPassword();

        SysUserEntity user = baseMapper.selectUserByName(username);

        //账号不存在
        if (user==null)
        {
            System.out.println("查无此人");
            return R.error("账号或密码错误");
        }


        //密码错误
        if ( !user.getPassword().equals(new Sha256Hash(userkey, user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        //修改活跃时间
        baseMapper.updateactivetimeByusername(username);


        //创建Token
        sysUserTokenService.createToken(user.getUserId(), loginform.getSaveLogin() == null ? false : loginform.getSaveLogin());

        return R.ok("ok");
    }

    @Override
    public R UserRegistByInviteCode(SysUserEntity userEntity){


        userEntity.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        userEntity.setPassword(new Sha256Hash(userEntity.getPassword(), salt).toHex());
        userEntity.setSalt(salt);

        // 判断手机号是否已存在
        if (isExistTheSameMobile(Long.valueOf(0), userEntity.getMobile()) > 0) {
            throw new RRException("该手机号已注册");
        }

        // 判断邮箱是否已存在
        if (isExistTheSameEmail(Long.valueOf(0), userEntity.getEmail()) > 0) {
            throw new RRException("该邮箱已注册");
        }

        // 保存用户信息
        this.save(userEntity);

        //检查角色是否越权
        // checkRole(user);

        //保存用户与角色关系
       // sysUserRoleService.saveOrUpdate(userEntity.getUserId(), userEntity.getRoleIdList());

        return R.ok("注册成功！");
    }

    @Override
    public boolean UserUpdate(SysUserEntity userEntity){
        return false;
    }




    private int isExistTheSameMobile(Long userId, String mobile) {
        return baseMapper.isExistTheSameMobile(userId, mobile);
    }

    private int isExistTheSameEmail(Long userId, String email) {
        return baseMapper.isExistTheSameEmail(userId, email);
    }




}
