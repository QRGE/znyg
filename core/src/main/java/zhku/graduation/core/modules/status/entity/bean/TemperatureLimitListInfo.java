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
@ApiModel(value = "鱼缸温度限制列表信息", description = "鱼缸温度限制")
public class TemperatureLimitListInfo {

    private Integer id;

    @ApiModelProperty("鱼缸id")
    private Integer nodeId;

    @ApiModelProperty("温度上限")
    private Double temperatureUpperLimit;

    @ApiModelProperty("温度下限")
    private Double temperatureLower;



    public TemperatureLimitListInfo (NodeStatus po) {
        id = po.getId();
        nodeId = po.getNodeId();
        temperatureUpperLimit = po.getTemperatureUpperLimit();
        temperatureLower = po.getTemperatureLowerLimit();
    }
}
