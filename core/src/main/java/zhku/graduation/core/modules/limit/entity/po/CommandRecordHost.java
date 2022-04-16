package zhku.graduation.core.modules.limit.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostDetail;


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
@TableName("znyg_command_record_host")
public class CommandRecordHost {

    /**
     * 记录编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 鱼缸节点id
     */
    @TableField("node_id")
    private Integer nodeId;

    /**
     * 控制对象, J-加热器, C-除菌器, D-灯光
     */
    @TableField("command_object")
    private String commandObject;

    /**
     * 控制命令格式为“KxxddssA”，“K”为数据包开始标志，“xx”为鱼缸编号，范围为“00-99”，“dd”为设置的温度目标值，“ss”分别代表要求灯光和除菌器开启或者关闭，“s”取值为”Y”表示开启，取值为“N”表示关闭，字母“A”为数据包结束标志和表示控制命令来自C#上位机程序。
     */
    @TableField("command_text")
    private String commandText;

    /**
     * 命令状态, 0-未执行, 1-已发送, 2-已执行, 3-执行失败
     */
    @TableField("command_status")
    private Integer commandStatus;

    /**
     * 创建时间（下达时间）
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 执行时间
     */
    @TableField("execute_time")
    private Date executeTime;

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

    public CommandRecordHost init() {
        createTime = new Date();
        isDel = 0;
        return this;
    }

    public CommandRecordHost parseFromDto(CommandRecordHostDetail dto) {
        id = dto.getId();
        nodeId = dto.getNodeId();
        commandObject = dto.getCommandObject();
        commandText = dto.getCommandText();
        commandStatus = dto.getCommandStatus();
        executeTime = dto.getExecuteTime();
        return this;
    }


}
