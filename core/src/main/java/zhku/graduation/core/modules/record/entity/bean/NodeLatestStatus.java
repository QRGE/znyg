package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author qr
 * @Date 2022/5/16-19:34
 */
@ApiModel("鱼缸最新状态")
@Getter
@Setter
public class NodeLatestStatus {

    @ApiModelProperty("节点id")
    private Integer nodeId;

    @ApiModelProperty("自动加热状态")
    private Boolean heaterStatus = false;

    @ApiModelProperty("除菌器状态")
    private Boolean degermingStatus = false;

    @ApiModelProperty("灯光状态")
    private Boolean lightStatus = false;

    @ApiModelProperty("水温范围上限")
    private Integer up;

    @ApiModelProperty("水温范围下限")
    private Integer low;

    public NodeLatestStatus(Integer nodeId) {
        this.nodeId = nodeId;
    }
}
