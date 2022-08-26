package io.iti.modules.datastore.validate;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @param
 * @author hele
 * @time 2022-08-04  17:10
 * @return
 */
@Data
public class HyetometerDataValiateType1Entity {
    @NotNull(message = "一次数据上报间隔内的降雨量数据不能为空")
    private Float value;
    @NotNull(message = "当日雨量累积值数据不能为空")
    private Float totalValue;
}
