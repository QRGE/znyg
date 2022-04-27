package zhku.graduation.core.modules.status.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.status.entity.po.NodeStatus;

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
    private Double temperatureLowerLimit;

    @ApiModelProperty("加热器状态，1-开启，0-关闭")
    private Integer heater;

    @ApiModelProperty("灯光状态，1-开启，0-关闭")
    private Integer light;

    @ApiModelProperty("除菌器状态，1-开启，0-关闭")
    private Integer degerming;



    public TemperatureLimitDetail parseFromPo(NodeStatus po) {
        nodeId = po.getNodeId();
        temperatureUpperLimit = po.getTemperatureUpperLimit();
        temperatureLowerLimit = po.getTemperatureLowerLimit();
        heater = po.getHeaterStatus();
        light = po.getLightStatus();
        degerming = po.getDegermingStatus();
        return this;
    }
}
