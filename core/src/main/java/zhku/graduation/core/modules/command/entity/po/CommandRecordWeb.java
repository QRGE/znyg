package zhku.graduation.core.modules.command.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;

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
@TableName("znyg_command_record_web")
public class CommandRecordWeb {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 控制命令格式为“KxxddssB”，“K”为数据包开始标志，“xx”为鱼缸编号，范围为“00-99”，“dd”为设置的温度目标值，“ss”分别代表要求灯光和除菌器开启或者关闭，“s”取值为”Y”表示开启，取值为“N”表示关闭，字母“B”为数据包结束标志和表示控制命令来自web程序。
     */
    @TableField("command_text")
    private String commandText;

    /**
     * 命令状态, 0-未执行, 1-已发送, 2-已执行
     */
    @TableField("command_status")
    private Integer commandStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建者id
     */
    @TableField("create_by")
    private Integer createBy;

    /**
     * 删除状态, 1-是, 0-否
     */
    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public CommandRecordWeb init() {
        createTime = new Date();
        isDel = 0;
        return this;
    }

    public CommandRecordWeb parseFromDto(CommandRecordWebDetail dto) {
        id = dto.getId();
        commandText = dto.getCommandText();
        commandStatus = dto.getCommandStatus();
        return this;
    }


}
