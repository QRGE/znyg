package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.basic.constant.Constant;
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

    private Double temperature;

    private String heaterStatusText;

    public RealTimeRecord parseFromPO(MonitorRecord po) {
        temperature = po.getTemperature();
        heaterStatusText = Constant.Status.valueOf(po.getHeaterStatus()).getName();
        return this;
    }
}
