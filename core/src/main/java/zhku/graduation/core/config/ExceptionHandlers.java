package zhku.graduation.core.config;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuilder sb = new StringBuilder();
        sb.append("不支持 ");
        sb.append(e.getMethod());
        sb.append(" 请求方法，");
        sb.append("支持方法: ");
        String [] methods = e.getSupportedMethods();
        assert methods != null;
        for(String str:methods){
            sb.append(str);
            sb.append("、");
        }
        String result = sb.toString();
        // 把最后一个顿号去掉
        result = result.substring(0, result.length()-1);
        return Result.error(10005, result);
    }

    @ExceptionHandler(InValidTokenException.class)
    public Result<?> handleInvalidTokenException() {
        return error(HttpStatus.NO_AUTH_ERROR);
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Result<?> handleAuthorizationException(AuthorizationException e){
        return error(HttpStatus.NO_AUTH_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException() {
        return error(HttpStatus.ERROR);
    }


}
