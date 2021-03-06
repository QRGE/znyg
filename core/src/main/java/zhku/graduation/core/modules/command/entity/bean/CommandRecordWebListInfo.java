package zhku.graduation.core.modules.command.entity.bean;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.util.AnalysisCommandUtil;

import java.util.Date;

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

    @ApiModelProperty("命令状态文本")
    private String commandStatusText;

    @ApiModelProperty("命令状态, 0-未执行, 1-已发送, 2-已执行")
    private Integer commandStatus;

    @ApiModelProperty
    private String tagType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public CommandRecordWebListInfo (CommandRecordWeb po) {
        id = po.getId();
        String commandText = po.getCommandText();
        if (StrUtil.isNotBlank(commandText)) {
            this.nodeId = AnalysisCommandUtil.getNodeId(commandText);
            this.temperature = AnalysisCommandUtil.getTemperature(commandText);
            this.light = Constant.CommandInstrumentStatus.select(AnalysisCommandUtil.getLight(commandText)).getName();
            this.degerming = Constant.CommandInstrumentStatus.select(AnalysisCommandUtil.getDegerming(commandText)).getName();
        }
        commandStatus = po.getCommandStatus();
        commandStatusText = Constant.CommandStatus.valueOf(po.getCommandStatus()).getName();
        tagType = Constant.TagType.valueOf(po.getCommandStatus()).getName();
        createTime = po.getCreateTime();
    }
}
