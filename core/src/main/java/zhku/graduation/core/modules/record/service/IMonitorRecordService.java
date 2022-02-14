package zhku.graduation.core.modules.record.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;

/**
 * <p>
 * 监测记录表服务类
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
public interface IMonitorRecordService extends IService<MonitorRecord> {

    IPage<MonitorRecordListInfo> pageMonitorRecord(MonitorRecordPageRequest request);

    MonitorRecordDetail getMonitorRecord(Integer dataId);

    boolean saveOrUpdateMonitorRecord(MonitorRecordDetail dto);

    boolean removeMonitorRecord(Integer dataId);
}
