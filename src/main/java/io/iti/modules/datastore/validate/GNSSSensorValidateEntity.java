package io.iti.modules.datastore.validate;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @param
 * @author hele
 * @time 2022-08-03  13:20
 * @return
 */
@Data
public class GNSSSensorValidateEntity {
    @NotBlank(message = "传感器名称不能为空")
    private String name;
    @Valid
    private GNSSDataValiateType1Entity data;
}


