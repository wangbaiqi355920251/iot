package io.iti.modules.iot.sys.controller;

import io.iti.common.utils.R;
import io.iti.modules.iot.sys.service.verlCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public class VerlCodeController {

    @Autowired
    verlCodeService verlCodeService;

    @GetMapping("/sys/captcha")
    public  Map<String, Object> getVerlCode() {

        return R.ok().put("verlCode",verlCodeService.getVerlCode()) ;

    }





}
