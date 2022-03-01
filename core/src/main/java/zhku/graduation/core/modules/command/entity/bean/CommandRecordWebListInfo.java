package zhku.graduation.core.modules.command.entity.bean;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.util.AnalysisCommandUtil;

/**
 * <p>
 * Web 端控制命令记录
 * </p>
 *
 * @author QR
 * @since 2022-03-01
 */
@Getter
@Setter
@ApiModel(value = "Web 端控制命令记录列表信息", description = "Web 端控制命令记录")
public class CommandRecordWebListInfo {

    private Integer id;

    @ApiModelProperty("鱼缸节点id")
    private String nodeId;

    private String temperature;

    private String light;

    private String degerming;

    @ApiModelProperty("控制命令格式为“KxxddssB”，“K”为数据包开始标志，“xx”为鱼缸编号，范围为“00-99”，“dd”为设置的温度目标值，“ss”分别代表要求灯光和除菌器开启或者关闭，“s”取值为”Y”表示开启，取值为“N”表示关闭，字母“B”为数据包结束标志和表示控制命令来自web程序。")
    private String commandText;

    @ApiModelProperty("命令状态, 0-未执行, 1-已发送, 2-已执行")
    private Integer commandStatus;


    @ApiModelProperty("创建者id")
    private Integer createBy;


    public CommandRecordWebListInfo (CommandRecordWeb po) {
        id = po.getId();
        String commandText = po.getCommandText();
        if (StrUtil.isNotBlank(commandText)) {
            this.commandText = commandText;
            this.nodeId = AnalysisCommandUtil.getNodeId(commandText);
            this.temperature = AnalysisCommandUtil.getTemperature(commandText);
            this.light = AnalysisCommandUtil.getLight(commandText);
            this.degerming = AnalysisCommandUtil.getDegerming(commandText);
        }
        commandStatus = po.getCommandStatus();
    }
}
