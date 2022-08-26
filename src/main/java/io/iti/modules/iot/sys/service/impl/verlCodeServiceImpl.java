package io.iti.modules.iot.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import io.iti.modules.iot.sys.dao.VerlCodeDao;
import io.iti.modules.iot.sys.entity.VerlCodeEntity;
import io.iti.modules.iot.sys.service.verlCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class verlCodeServiceImpl extends ServiceImpl<VerlCodeDao, VerlCodeEntity> implements verlCodeService {

    @Autowired
    private Producer producer;
   public String getVerlCode(){



       return null;
    }

    public Boolean judgeVerlCode(String uuid,String inputCode){


       return false;
    }

}
