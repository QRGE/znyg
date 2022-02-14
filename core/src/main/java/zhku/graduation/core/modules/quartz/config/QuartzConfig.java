package zhku.graduation.core.modules.quartz.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zhku.graduation.core.modules.quartz.job.MockRecordJob;

/**
 * 自动配置 quartz
 * @author qr
 * @date 2022/2/14 09:20
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail job() {
        return JobBuilder.newJob(MockRecordJob.class)
                .withIdentity("JobDetail-1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        // 简单的调度计划的构造器
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                // 频率
                .withIntervalInSeconds(5)
                // 次数
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(job())
                .withIdentity("Trigger-1")
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
    }
}
