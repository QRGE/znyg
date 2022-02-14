package zhku.graduation.core.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.mapper.MonitorRecordMapper;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.Optional;


/**
 * <p>
 * 监测记录表 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
@Service
public class MonitorRecordServiceImpl extends ServiceImpl<MonitorRecordMapper, MonitorRecord> implements IMonitorRecordService {

    @Override
    public IPage<MonitorRecordListInfo> pageMonitorRecord(MonitorRecordPageRequest request) {
        LambdaQueryWrapper<MonitorRecord> queryWrapper = baseQueryWrapper();
        IPage<MonitorRecord> page = new Page<>(request.getPage(), request.getPageSize());
        page = this.page(page, queryWrapper);
        return page.convert(MonitorRecordListInfo::new);
    }

    @Override
    public MonitorRecordDetail getMonitorRecord(Integer dataId) {
        MonitorRecord monitorRecord = getById(dataId);
        return Optional.ofNullable(monitorRecord)
                        .map(po -> new MonitorRecordDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateMonitorRecord(MonitorRecordDetail dto) {
        MonitorRecord monitorRecord = new MonitorRecord();
        if(dto.getId() == null){
            monitorRecord = monitorRecord.init();
        }
        monitorRecord.parseFromDto(dto);
        return saveOrUpdate(monitorRecord);
    }

    @Override
    public boolean removeMonitorRecord(Integer dataId) {
        return removeById(dataId);
    }

    private LambdaQueryWrapper<MonitorRecord> baseQueryWrapper() {
        return Wrappers.lambdaQuery(MonitorRecord.class)
                        .eq(MonitorRecord::getIsDel, 0);
    }

    private LambdaUpdateWrapper<MonitorRecord> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(MonitorRecord.class)
                        .eq(MonitorRecord::getIsDel, 0);
    }
}
