package zhku.graduation.core.modules.record.entity.bean;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

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
    private String temperature = "未获取";

    /**
     * 加热器自动控制状态
     */
    private boolean heaterAutoStatus = false;

    /**
     * 温度状态
     */
    private String temperatureRange = "未获取";


    private boolean heaterStatus = false;
    private String heaterStatusText = "未获取";

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
    private String recordTime = "未获取";

    public LatestRecord parseFromPO(MonitorRecord po) {
        if (po.getTemperature() != null) {
            this.temperature = po.getTemperature() + "°C";
        }
        this.heaterAutoStatus = !po.getHeaterAutoStatus().equals(0);
        if (po.getTemperatureLowerLimit() != null && po.getTemperatureUpperLimit() != null) {
            this.temperatureRange = po.getTemperatureLowerLimit().intValue() + " ~ " +po.getTemperatureUpperLimit().intValue() + "°C";
        }
        heaterStatus = !po.getHeaterStatus().equals(0);
        // 查询鱼缸的自动加热状态
        if (po.getHeaterStatus().equals(0)) {
            this.heaterStatusText = "关闭";
        }else {
            this.heaterStatusText = "开启";
        }
        lightStatus = !po.getLightStatus().equals(0);
        degermingStatus = !po.getDegermingStatus().equals(0);
        if (po.getRecordTime() != null) {
            recordTime = DateUtil.format(po.getRecordTime(), "yyyy-MM-dd HH:mm:ss");
        }
        return this;
    }
}
