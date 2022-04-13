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

    private List<Integer> temperatures;

    private List<String> dates;

    private LatestRecord record;

    private String heaterStatusText;

    public RealTimeRecord parseFromPO(MonitorRecord po) {
        this.record = new LatestRecord().parseFromPO(po);
        this.heaterStatusText = Constant.Status.valueOf(po.getHeaterStatus()).getName();
        return this;
    }
}
