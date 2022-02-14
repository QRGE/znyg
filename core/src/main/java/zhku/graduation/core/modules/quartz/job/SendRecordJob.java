package zhku.graduation.core.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;
import zhku.graduation.core.modules.socket.WebSocket;

/**
 * @author qr
 * @date 2022/2/14 14:45
 */
@Component
@Slf4j
public class SendRecordJob extends QuartzJobBean {

    @Autowired
    private IMonitorRecordService monitorRecordService;
    @Autowired
    private WebSocket webSocket;

    /**
     * 推送最新的监控历史记录
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        LambdaQueryWrapper<MonitorRecord> wrapper = Wrappers.lambdaQuery(MonitorRecord.class)
                .orderByDesc(MonitorRecord::getRecordTime)
                .last("limit 1");
        MonitorRecord record = monitorRecordService.getOne(wrapper);
        webSocket.sendAllMessage(record.toString());
        log.info("发送最新监控历史数据: {}", record);
    }
}
