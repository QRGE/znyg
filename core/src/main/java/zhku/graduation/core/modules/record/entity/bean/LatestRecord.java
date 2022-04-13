package zhku.graduation.core.modules.record.entity.bean;

import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

import java.util.Date;

/**
 * @author qr
 * @date 2022/4/13 22:15
 */
@Getter
@Setter
public class LatestRecord {

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 加热器自动控制状态
     */
    private boolean heaterAutoStatus = false;

    /**
     * 温度上限
     */
    private Integer temperatureUpperLimit;

    /**
     * 温度下限
     */
    private Integer temperatureLowerLimit;

    private boolean heaterStatus = false;

    /**
     * 灯光状态, 0-关闭, 1-开启
     */
    private boolean lightStatus = false;

    /**
     * 除菌器状态, 0-关闭, 1-开启
     */
    private boolean degermingStatus = false;

    /**
     * 记录时间
     */
    private Date recordTime;

    public LatestRecord parseFromPO(MonitorRecord po) {
        this.temperature = po.getTemperature();
        this.heaterAutoStatus = !po.getHeaterAutoStatus().equals(0);
        temperatureUpperLimit = po.getTemperatureUpperLimit().intValue();
        temperatureLowerLimit = po.getTemperatureLowerLimit().intValue();
        heaterStatus = !po.getHeaterStatus().equals(0);
        lightStatus = !po.getLightStatus().equals(0);
        degermingStatus = !po.getDegermingStatus().equals(0);
        recordTime = po.getRecordTime();
        return this;
    }
}
