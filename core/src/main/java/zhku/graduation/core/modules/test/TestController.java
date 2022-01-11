package zhku.graduation.core.modules.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.vo.Result;

/**
 * @author qr
 * @date 2021/12/26 00:33
 */
@Api(tags = "测试接口")
@RestController
public class TestController {

    @ApiOperation("测试controller")
    @GetMapping("/test")
    public Result<?> test(){
        return Result.OK("测试接口");
    }

    @ApiOperation("打个招呼")
    @GetMapping("/hello")
    public Result<?> hello(){
        return Result.OK("你好哇");
    }
}
