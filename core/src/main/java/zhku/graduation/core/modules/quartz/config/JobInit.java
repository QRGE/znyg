package zhku.graduation.core.modules.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobInit implements ApplicationRunner {


    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) {
        // mock 数据任务
//        JobDetail jobMock = JobBuilder.newJob(MockRecordJob.class)
//                .withIdentity(Constant.JOB_MOCK)
//                .storeDurably()
//                .build();
//        CronScheduleBuilder cron1 = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
//        Trigger trigger1 = TriggerBuilder.newTrigger()
//                .forJob(jobMock)
//                .withIdentity(Constant.TRIGGER_1)
//                .withSchedule(cron1)
//                .startNow()
//                .build();
//        scheduler.scheduleJob(jobMock, trigger1);
    }
}

