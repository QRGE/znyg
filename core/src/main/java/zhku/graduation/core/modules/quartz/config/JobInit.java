package zhku.graduation.core.modules.quartz.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.core.modules.quartz.job.MockRecordJob;

@Component
public class JobInit implements ApplicationRunner {


    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // mock 数据任务
        JobDetail jobMock = JobBuilder.newJob(MockRecordJob.class)
                .withIdentity(Constant.JOB_MOCK)
                .storeDurably()
                .build();
        CronScheduleBuilder cron1 = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .forJob(jobMock)
                .withIdentity(Constant.TRIGGER_1)
                .withSchedule(cron1)
                .startNow()
                .build();
        scheduler.scheduleJob(jobMock, trigger1);
//        // 推送最新监控记录任务
//        JobDetail jobSendRecord = JobBuilder.newJob(SendRecordJob.class)
//                .withIdentity(Constant.JOB_GET_LATEST_RECORD)
//                .storeDurably()
//                .build();
//        CronScheduleBuilder cron2 = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
//        CronTrigger trigger2 = TriggerBuilder.newTrigger()
//                .forJob(jobSendRecord)
//                .withIdentity(Constant.TRIGGER_2)
//                .withSchedule(cron2)
//                .startNow()
//                .build();
//        scheduler.scheduleJob(jobSendRecord, trigger2);
    }
}

