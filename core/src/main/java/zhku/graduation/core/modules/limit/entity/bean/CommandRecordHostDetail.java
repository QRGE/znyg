package zhku.graduation.core.modules.limit.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.limit.entity.po.CommandRecordHost;

import java.util.Date;

/**
 * <p>
 * 上位机 端控制命令记录
 * </p>
 *
 * @author QR
 * @since 2022-04-16
 */
@Getter
@Setter
@ApiModel(value = "上位机 端控制命令记录详情")
public class CommandRecordHostDetail {

    @ApiModelProperty("记录编号")
    private Integer id;

    @ApiModelProperty("鱼缸节点id")
    private Integer nodeId;

    @ApiModelProperty("鱼缸名字")
    private String nodeName;

    @ApiModelProperty("控制对象, J-加热器, C-除菌器, D-灯光")
    private String commandObject;

    @ApiModelProperty("控制命令格式为“KxxddssA”，“K”为数据包开始标志，“xx”为鱼缸编号，范围为“00-99”，“dd”为设置的温度目标值，“ss”分别代表要求灯光和除菌器开启或者关闭，“s”取值为”Y”表示开启，取值为“N”表示关闭，字母“A”为数据包结束标志和表示控制命令来自C#上位机程序。")
    private String commandText;

    @ApiModelProperty("命令状态, 0-未执行, 1-已发送, 2-已执行, 3-执行失败")
    private Integer commandStatus;


    @ApiModelProperty("执行时间")
    private Date executeTime;

    @ApiModelProperty("创建者id")
    private Integer createBy;


    public CommandRecordHostDetail parseFromPo(CommandRecordHost po) {
        id = po.getId();
        nodeId = po.getNodeId();
        commandObject = po.getCommandObject();
        commandText = po.getCommandText();
        commandStatus = po.getCommandStatus();
        executeTime = po.getExecuteTime();
        return this;
    }
}
