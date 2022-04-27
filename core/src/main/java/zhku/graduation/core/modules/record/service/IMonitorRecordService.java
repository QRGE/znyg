package zhku.graduation.core.modules.record.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

import java.util.Date;

/**
 * <p>
 * 监测记录表服务类
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
public interface IMonitorRecordService extends IService<MonitorRecord> {

    boolean saveOrUpdateMonitorRecord(MonitorRecordDetail dto);

    boolean removeMonitorRecord(Integer dataId);

    Page<MonitorRecordListInfo> pageMonitorRecords(Integer nodeId, Date startTime, Date endTime, Integer orderType, Integer pageStart, Integer pageSize);
}
