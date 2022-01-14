package zhku.graduation.core.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zhku.graduation.basic.constant.HttpStatus;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.exception.InValidTokenException;
import zhku.graduation.basic.vo.Result;

/**
 * ControllerAdvice 只能处理 Controller 层的抛出的异常
 * @author qr
 * @date 2022/1/14 23:47
 */
@RestControllerAdvice
public class ExceptionHandlers extends BaseController {

    @ExceptionHandler(Exception.class)
    public Result<?> handleException() {
        return error(HttpStatus.ERROR);
    }

    @ExceptionHandler(InValidTokenException.class)
    public Result<?> handleInvalidTokenException() {
        return error(HttpStatus.NO_AUTH_ERROR);
    }
}
