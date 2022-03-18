package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

import java.util.List;

/**
 * @author qr
 * @date 2022/3/18 14:08
 */
@ApiModel("实时记录")
@Getter
@Setter
public class RealTimeRecord {

    private List<Double> temperatures;

    private List<String> dates;

    private Integer heater;

    private Integer light;

    private Integer degerming;

    private Double temperature;

    public RealTimeRecord parseFromPO(MonitorRecord po) {
        heater = po.getHeaterStatus();
        light = po.getLightStatus();
        degerming = po.getDegermingStatus();
        temperature = po.getTemperature();
        return this;
    }
}
