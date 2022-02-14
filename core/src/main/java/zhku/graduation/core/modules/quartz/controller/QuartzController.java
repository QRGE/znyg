package zhku.graduation.core.modules.quartz.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.controller.BaseController;

/**
 * @author qr
 * @date 2022/2/13 16:52
 */
@Api(tags = "定时任务")
@RestController
@RequestMapping("/quartz/")
@Slf4j
public class QuartzController extends BaseController {
}
