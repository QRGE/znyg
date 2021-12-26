package zhku.graduation.core.modules.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.vo.Result;

/**
 * @author qr
 * @date 2021/12/26 00:33
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/")
public class TestController {

    @ApiOperation("测试controller")
    @GetMapping("/")
    public Result<?> test(){
        return Result.OK("你好哇");
    }
}
