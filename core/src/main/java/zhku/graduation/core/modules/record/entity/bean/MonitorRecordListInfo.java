package zhku.graduation.core.modules.record.entity.bean;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.constant.Constant;
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

    @ApiModelProperty("记录id")
    private Integer id;

    @ApiModelProperty("温度")
    private Double temperature;

    @ApiModelProperty("鱼缸节点id")
    private Integer nodeId;

    @ApiModelProperty("节点名称")
    private String nodeName;

    @ApiModelProperty("加热器自动控制状态, 0-关闭, 1-开启")
    private String heaterAutoStatus;

    @ApiModelProperty("加热器状态, 0-关闭, 1-开启")
    private String heaterStatus;

    @ApiModelProperty("灯光状态, 0-关闭, 1-开启")
    private String lightStatus;

    @ApiModelProperty("除菌器状态, 0-关闭, 1-开启")
    private String degermingStatus;

    @ApiModelProperty("温度上限")
    private Double upperLimit;

    @ApiModelProperty("温度下限")
    private Double lowerLimit;

    @ApiModelProperty("记录时间")
    private String recordTime;


    public MonitorRecordListInfo (MonitorRecord po, String nodeName) {
        id = po.getId();
        temperature = po.getTemperature();
        this.nodeName = nodeName;
        nodeId = po.getNodeId();
        heaterAutoStatus = Constant.Status.valueOf(po.getHeaterAutoStatus()).getName();
        heaterStatus = Constant.Status.valueOf(po.getHeaterStatus()).getName();
        lightStatus = Constant.Status.valueOf(po.getLightStatus()).getName();
        degermingStatus = Constant.Status.valueOf(po.getDegermingStatus()).getName();
        upperLimit = po.getTemperatureUpperLimit();
        lowerLimit = po.getTemperatureLowerLimit();
        recordTime = DateUtil.format(po.getRecordTime(), Constant.dateTimeFormat);
    }
}
