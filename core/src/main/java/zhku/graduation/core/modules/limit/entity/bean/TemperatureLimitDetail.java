package zhku.graduation.core.modules.limit.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.limit.entity.po.TemperatureLimit;

/**
 * <p>
 * 鱼缸温度限制
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
@Getter
@Setter
@ApiModel(value = "鱼缸温度限制详情")
public class TemperatureLimitDetail {

    @ApiModelProperty("鱼缸id")
    private Integer nodeId;

    @ApiModelProperty("温度上限")
    private Double temperatureUpperLimit;

    @ApiModelProperty("温度下限")
    private Double temperatureLower;



    public TemperatureLimitDetail parseFromPo(TemperatureLimit po) {
        nodeId = po.getNodeId();
        temperatureUpperLimit = po.getTemperatureUpperLimit();
        temperatureLower = po.getTemperatureLower();
        return this;
    }
}
