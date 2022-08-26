package io.iti.modules.datastore.validate;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @param
 * @author hele
 * @time 2022-08-03  13:38
 * @return
 */
@Data
public class GNSSDataValiateType1Entity {
    @NotNull(message = "GNSS监测点初始位置X向位移差值数据不能为空")
    private Float gpsTotalX;
    @NotNull(message = "GNSS监测点初始位置Y向位移差值数据不能为空")
    private Float gpsTotalY;
    @NotNull(message = "GNSS监测点初始位置Z向位移差值数据不能为空")
    private Float gpsTotalZ;
}
