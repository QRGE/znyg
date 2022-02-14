package zhku.graduation.core.modules.quartz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;

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
            scheduler.pauseTrigger(TriggerKey.triggerKey(Constant.JOB_DETAIL));
            scheduler.unscheduleJob(TriggerKey.triggerKey(Constant.JOB_DETAIL));
            scheduler.deleteJob(JobKey.jobKey(Constant.JOB_DETAIL));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Result.OK();
    }
}
