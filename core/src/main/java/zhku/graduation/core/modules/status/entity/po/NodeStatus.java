package zhku.graduation.core.modules.status.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitDetail;

import java.util.Date;


/**
 * <p>
 * 鱼缸温度限制
 * </p>
 *
 * @author QR
 * @since 2022-04-04
 */
@Getter
@Setter
@TableName("znyg_node_status")
public class NodeStatus {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 鱼缸id
     */
    @TableField("node_id")
    private Integer nodeId;

    /**
     * 温度上限
     */
    @TableField("temperature_upper_limit")
    private Double temperatureUpperLimit;

    /**
     * 温度下限
     */
    @TableField("temperature_lower_limit")
    private Double temperatureLowerLimit;

    /**
     * 加热器状态
     */
    @TableField("heater_status")
    private Integer heaterStatus;

    /**
     * 灯光状态
     */
    @TableField("light_status")
    private Integer lightStatus;

    /**
     * 除菌器状态
     */
    @TableField("degerming_status")
    private Integer degermingStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public NodeStatus init() {
        createTime = new Date();
        isDel = 0;
        return this;
    }

    public NodeStatus parseFromDTO(TemperatureLimitDetail dto) {
        nodeId = dto.getNodeId();
        temperatureUpperLimit = dto.getTemperatureUpperLimit();
        temperatureLowerLimit = dto.getTemperatureLowerLimit();
        return this;
    }


}
