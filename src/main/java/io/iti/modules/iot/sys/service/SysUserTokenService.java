package io.iti.modules.iot.sys.service;
import io.iti.common.utils.R;
public interface SysUserTokenService {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    R createToken(long userId, Boolean saveLogin);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);
}
