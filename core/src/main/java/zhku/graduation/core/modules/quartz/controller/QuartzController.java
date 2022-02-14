package zhku.graduation.core.modules.quartz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.quartz.job.MockRecordJob;
import zhku.graduation.core.modules.quartz.job.SendRecordJob;

/**
 * @author qr
 * @date 2022/2/13 16:52
 */
@Api(tags = "定时任务")
@RestController
@RequestMapping("/quartz/")
@Slf4j
public class QuartzController extends BaseController {

    @Autowired
    private Scheduler scheduler;

    @ApiOperation("暂停历史mock数据任务")
    @GetMapping("mock/pause")
    public Result<?> pauseMock(){
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(Constant.JOB_MOCK));
            scheduler.unscheduleJob(TriggerKey.triggerKey(Constant.JOB_MOCK));
            scheduler.deleteJob(JobKey.jobKey(Constant.JOB_MOCK));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("删除定时任务：{}", Constant.JOB_MOCK);
        return Result.OK();
    }

    @ApiOperation("开始执行历史mock数据任务")
    @GetMapping("mock/start")
    public Result<?> startMock() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(MockRecordJob.class)
                .withIdentity(Constant.JOB_MOCK)
                .storeDurably()
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(Constant.TRIGGER_1)
                .withSchedule(scheduleBuilder)
                .startNow() //立即執行一次任務
                .build();
        // 手动将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("开始执行定时任务：{}", Constant.JOB_MOCK);
        return Result.OK();
    }

    @ApiOperation("暂停历史mock数据任务")
    @GetMapping("data/pause")
    public Result<?> pauseGetLatestData(){
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(Constant.JOB_GET_LATEST_RECORD));
            scheduler.unscheduleJob(TriggerKey.triggerKey(Constant.JOB_GET_LATEST_RECORD));
            scheduler.deleteJob(JobKey.jobKey(Constant.JOB_GET_LATEST_RECORD));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("删除定时任务：{}", Constant.JOB_GET_LATEST_RECORD);
        return Result.OK();
    }

    @ApiOperation("开始执行历史mock数据任务")
    @GetMapping("data/start")
    public Result<?> startGetLatestData() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SendRecordJob.class)
                .withIdentity(Constant.JOB_GET_LATEST_RECORD)
                .storeDurably()
                .build();
        // 每秒钟推送一次最新的记录
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(Constant.TRIGGER_1)
                .withSchedule(scheduleBuilder)
                .startNow() //立即執行一次任務
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("开始执行定时任务：{}", Constant.JOB_GET_LATEST_RECORD);
        return Result.OK();
    }
}
