package zhku.graduation.core.modules.record.entity.bean;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

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
@ApiModel(value = "监测记录表列表信息", description = "监测记录表")
@ToString
public class MonitorRecordListInfo {

    @ApiModelProperty("温度")
    private Double temperature;

    @ApiModelProperty("鱼缸节点id")
    private Integer nodeId;

    @ApiModelProperty("加热器状态, 0-关闭, 1-开启")
    private Integer heaterStatus;

    @ApiModelProperty("灯光状态, 0-关闭, 1-开启")
    private Integer lightStatus;

    @ApiModelProperty("除菌器状态, 0-关闭, 1-开启")
    private Integer degermingStatus;

    @ApiModelProperty("记录时间")
    private String recordTime;


    public MonitorRecordListInfo (MonitorRecord po) {
        temperature = po.getTemperature();
        nodeId = po.getNodeId();
        heaterStatus = po.getHeaterStatus();
        lightStatus = po.getLightStatus();
        degermingStatus = po.getDegermingStatus();
        recordTime = DateUtil.format(po.getRecordTime(), "HH:mm:ss");
    }
}
