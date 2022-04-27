package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import java.util.Date;

/**
 * <p>
 * 监测记录表
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
@Getter
@Setter
@ApiModel(value = "监测记录表详情")
public class MonitorRecordDetail {

    private Integer id;

    @ApiModelProperty("鱼缸节点id")
    private Integer nodeId;

    @ApiModelProperty("温度")
    private Double temperature;

    @ApiModelProperty("加热器状态, 0-关闭, 1-开启")
    private Integer heaterStatus;

    @ApiModelProperty("灯光状态, 0-关闭, 1-开启")
    private Integer lightStatus;

    @ApiModelProperty("除菌器状态, 0-关闭, 1-开启")
    private Integer degermingStatus;

    @ApiModelProperty("记录时间")
    private Date recordTime;


    public MonitorRecordDetail parseFromPo(MonitorRecord po) {
        id = po.getId();
        nodeId = po.getNodeId();
        temperature = po.getTemperature();
        heaterStatus = po.getHeaterStatus();
        lightStatus = po.getLightStatus();
        degermingStatus = po.getDegermingStatus();
        recordTime = po.getRecordTime();
        return this;
    }
}
