package zhku.graduation.core.modules.record.entity.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

import java.util.Date;
import java.util.List;

/**
 * @author qr
 * @date 2022/3/18 14:08
 */
@ApiModel("实时记录")
@Getter
@Setter
public class RealTimeRecord {

    private List<Integer> temperatures;

    private List<String> dates;

    private Integer temperature;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate;

    private String heaterStatusText;

    public RealTimeRecord parseFromPO(MonitorRecord po) {
        temperature = po.getTemperature().intValue();
        heaterStatusText = Constant.Status.valueOf(po.getHeaterStatus()).getName();
        recordDate = po.getRecordTime();
        return this;
    }
}
