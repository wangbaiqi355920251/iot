package io.iti.modules.iot.sys.service;

public interface verlCodeService {

    String getVerlCode();

    Boolean judgeVerlCode(String uuid,String inputCode);
}
