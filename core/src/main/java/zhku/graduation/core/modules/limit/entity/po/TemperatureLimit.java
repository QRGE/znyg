package zhku.graduation.core.modules.limit.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitDetail;

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
@TableName("znyg_temperature_limit")
public class TemperatureLimit {

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

    @TableField("create_time")
    private Date createTime;

    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public TemperatureLimit init() {
        createTime = new Date();
        isDel = 0;
        return this;
    }

    public TemperatureLimit parseFromDTO(TemperatureLimitDetail dto) {
        nodeId = dto.getNodeId();
        temperatureUpperLimit = dto.getTemperatureUpperLimit();
        temperatureLowerLimit = dto.getTemperatureLowerLimit();
        return this;
    }


}
