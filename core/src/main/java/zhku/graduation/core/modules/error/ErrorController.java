package zhku.graduation.core.modules.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.constant.HttpStatus;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;

/**
 * @author qr
 * @date 2022/1/15 00:12
 */
@RestController
public class ErrorController extends BaseController {

    @GetMapping("/error")
    public Result<?> get(){
        return error(HttpStatus.NO_AUTH_ERROR);
    }
}
