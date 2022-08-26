package io.iti.modules.datastore.validate;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @param
 * @author hele
 * @time 2022-08-03  9:04
 * @return
 */
@Data
public class GNSSDeviceInfoValidateEntity {
    @NotBlank(message = "设备名不能为空")
    private String device_name;
    @NotBlank(message = "设备SN不能为空")
    private String device_sn;
    @Valid
    private List<GNSSSensorValidateEntity> sensor;
    @Size(min = 10, max = 10, message = "时间戳必须为10位")
    private String date;
    private String desc;
}
