package zhku.graduation.core.modules.quartz.job;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import zhku.graduation.core.modules.node.service.INodeService;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 创建历史记录的假数据
 * @author qr
 * @date 2022/2/13 17:14
 */
@Component
@Slf4j
public class MockRecordJob extends QuartzJobBean {

    @Autowired
    private IMonitorRecordService monitorRecordService;
    @Autowired
    private INodeService nodeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        MonitorRecord monitorRecord = new MonitorRecord();
        // 鱼缸节点id
        List<Integer> nodeIds = nodeService.getNodeIds();
        Integer nodeId = RandomUtil.randomEle(nodeIds);
        monitorRecord.setNodeId(nodeId);
        // 温度
        monitorRecord.setTemperature(BigDecimal.valueOf(RandomUtil.randomDouble(9.1, 38.4)).setScale(1, RoundingMode.HALF_UP).doubleValue());
        // 记录日期
        monitorRecord.setRecordTime(new Date());
        // 保存到数据库
        monitorRecordService.save(monitorRecord);
        log.debug("生成 mock 数据, id: {}", monitorRecord.getId());
    }

}
