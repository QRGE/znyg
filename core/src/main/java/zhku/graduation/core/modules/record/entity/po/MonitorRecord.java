package zhku.graduation.core.modules.record.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;


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
@TableName("znyg_monitor_record")
public class MonitorRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 鱼缸节点id
     */
    @TableField("node_id")
    private Integer nodeId;

    /**
     * 温度
     */
    @TableField("temperature")
    private Double temperature;

    /**
     * 加热器状态, 0-关闭, 1-开启
     */
    @TableField("heater_status")
    private Integer heaterStatus;

    /**
     * 灯光状态, 0-关闭, 1-开启
     */
    @TableField("light_status")
    private Integer lightStatus;

    /**
     * 除菌器状态, 0-关闭, 1-开启
     */
    @TableField("degerming_status")
    private Integer degermingStatus;

    /**
     * 记录时间
     */
    @TableField("record_time")
    private Date recordTime;

    /**
     * 删除状态, 1-是 0-否
     */
    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public MonitorRecord init() {
        isDel = 0;
        return this;
    }

    public MonitorRecord parseFromDto(MonitorRecordDetail dto) {
        id = dto.getId();
        nodeId = dto.getNodeId();
        temperature = dto.getTemperature();
        heaterStatus = dto.getHeaterStatus();
        lightStatus = dto.getLightStatus();
        degermingStatus = dto.getDegermingStatus();
        recordTime = dto.getRecordTime();
        return this;
    }


}
