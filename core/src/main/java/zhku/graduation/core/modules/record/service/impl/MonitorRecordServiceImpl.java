package zhku.graduation.core.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.entity.request.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.mapper.MonitorRecordMapper;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.Date;


/**
 * <p>
 * 监测记录表 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-02-14
 */
@Slf4j
@Service
public class MonitorRecordServiceImpl extends ServiceImpl<MonitorRecordMapper, MonitorRecord> implements IMonitorRecordService {

    @Override
    public IPage<MonitorRecordListInfo> pageMonitorRecord(MonitorRecordPageRequest request) {
        LambdaQueryWrapper<MonitorRecord> queryWrapper = baseQueryWrapper();
        Date startTime = request.getStartTime();
        if (startTime != null) {
            queryWrapper.ge(MonitorRecord::getRecordTime, startTime);
        }
        Date endTime = request.getEndTime();
        if (endTime != null) {
            queryWrapper.le(MonitorRecord::getRecordTime, endTime);
        }
        Constant.OrderType type = Constant.OrderType.valueOf(request.getOrderType());
        switch (type) {
            case ASC:
                queryWrapper.orderByAsc(MonitorRecord::getRecordTime);
                break;
            case DESC:
                queryWrapper.orderByDesc(MonitorRecord::getRecordTime);
                break;
        }
        IPage<MonitorRecord> page = new Page<>(request.getPage(), request.getPageSize());
        page = page(page, queryWrapper);
        log.info("查询记录条数：{}", page.getTotal());
        return page.convert(MonitorRecordListInfo::new);
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
