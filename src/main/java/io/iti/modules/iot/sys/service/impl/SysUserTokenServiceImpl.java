package io.iti.modules.iot.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.iti.common.utils.R;
import io.iti.modules.iot.sys.dao.SysUserTokenDao;
import io.iti.modules.iot.sys.entity.SysUserTokenEntity;
import io.iti.modules.iot.sys.oauth.TokenGenerator;
import io.iti.modules.iot.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("SysUserTokenService")
public class SysUserTokenServiceImpl  extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService{

    // 三天后过期
    private final static int EXPIRE = 259200;

    @Override
    public R createToken(long userId, Boolean saveLogin){

        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = saveLogin ? new Date(now.getTime() + (3600 * 24 * 30L * 1000)) : new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    @Override
    public void logout(long userId){

    }
}
