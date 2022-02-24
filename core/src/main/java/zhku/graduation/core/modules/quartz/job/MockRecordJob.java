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
        // 除菌器状态
        monitorRecord.setDegermingStatus(RandomUtil.randomInt(0, 2));
        // 加热器状态
        monitorRecord.setHeaterStatus(RandomUtil.randomInt(0, 2));
        // 灯光状态
        monitorRecord.setLightStatus(RandomUtil.randomInt(0, 2));
        // 鱼缸节点
        Integer nodeSize = nodeService.getNodeSize();
        monitorRecord.setNodeId(RandomUtil.randomInt(1, nodeSize+1));
        // 温度
        monitorRecord.setTemperature(BigDecimal.valueOf(RandomUtil.randomDouble(9.1, 38.4)).setScale(1, RoundingMode.HALF_UP).doubleValue());
        // 记录日期
        monitorRecord.setRecordTime(new Date());
        // 保存到数据库
        monitorRecordService.save(monitorRecord);
        log.info("生成 mock 数据, id: {}", monitorRecord.getId());
    }

}
