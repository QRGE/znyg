package zhku.graduation.core.modules.quartz.job;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

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
        monitorRecord.setNodeId(RandomUtil.randomInt(1, 5));
        // 温度
        monitorRecord.setTemperature(BigDecimal.valueOf(RandomUtil.randomDouble(12.1, 28.4)).setScale(1, RoundingMode.HALF_UP).doubleValue());
        // 记录日期
        DateTime start = DateUtil.parse("2022-01-01", "yyyy-MM-dd");
        DateTime month = RandomUtil.randomDate(start, DateField.MONTH, 0, 4);
        DateTime day = RandomUtil.randomDate(month, DateField.DAY_OF_MONTH, 1, 31);
        DateTime hour = RandomUtil.randomDate(day, DateField.HOUR_OF_DAY, 0, 24);
        DateTime min = RandomUtil.randomDate(hour, DateField.MINUTE, 0, 60);
        DateTime second = RandomUtil.randomDate(min, DateField.SECOND, 0, 60);
        monitorRecord.setRecordTime(second);
        // 保存到数据库
        monitorRecordService.save(monitorRecord);
        log.info("生成mock记录数据：{}", monitorRecord);
    }

    public static void main(String[] args) {
        Random status = new Random();
        for (int i = 0; i < 10000; i++) {
            System.out.println(status.nextInt(2));
        }
    }
}
