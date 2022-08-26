package io.iti.modules.iot.sys.controller;

import io.iti.common.utils.R;
import io.iti.modules.iot.sys.form.loginForm;
import io.iti.modules.iot.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SysUserLoginController {

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/sys/login")
    public R login(@RequestBody loginForm form) throws IOException {
//        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//        if (!captcha) {
//            return R.error("验证码不正确");
//        }

        // 用户信息
        // SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
       R r =  sysUserService.UserLogin(form);


        //更新activetime
 //       sysUserService.updateactivetimeByusername(user.getUsername());
        //生成token，并保存到数据库
  //      R r = sysUserTokenService.createToken(user.getUserId(), form.getSaveLogin() == null ? false : form.getSaveLogin());

        return r;
    }




}
