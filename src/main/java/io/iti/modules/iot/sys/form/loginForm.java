package io.iti.modules.iot.sys.form;

import lombok.Data;
@Data
public class loginForm {
    /**
     * 登录表单
    *
      */


        private String username;
        private String password;
        private String captcha;
        private String uuid;
        private Boolean saveLogin;

}
