package zhku.graduation.core.modules.record.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.mapper.MonitorRecordMapper;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public Page<MonitorRecordListInfo> pageMonitorRecords(Integer nodeId, Date startTime, Date endTime, Integer orderType, Integer pageStart, Integer pageSize) {
        Page<MonitorRecordListInfo> page = new Page<>();
        LambdaQueryWrapper<MonitorRecord> queryWrapper = Wrappers.lambdaQuery(MonitorRecord.class);
        if (nodeId != null) {
            queryWrapper.eq(MonitorRecord::getNodeId, nodeId);
        }
        if (startTime != null) {
            queryWrapper.ge(MonitorRecord::getRecordTime, startTime);
        }
        if (endTime != null) {
            queryWrapper.le(MonitorRecord::getRecordTime, endTime);
        }
        long count = count(queryWrapper);
        page.setCount(count);
        queryWrapper.last("limit " + pageSize+ " offset " + pageStart);
        if (orderType.equals(Constant.OrderType.DESC.getType())) {
            queryWrapper.orderByDesc(MonitorRecord::getRecordTime);
        }else {
            queryWrapper.orderByAsc(MonitorRecord::getRecordTime);
        }
        List<MonitorRecord> list = list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            List<MonitorRecordListInfo> resultList = list.stream().map(MonitorRecordListInfo::new)
                    .collect(Collectors.toList());
            page.setRecords(resultList);
        }
        Long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        page.setTotalPage(totalPage);
        return page;
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
