package zhku.graduation.core.common.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static zhku.graduation.basic.constant.HttpStatus.AUTH_ERROR;
import static zhku.graduation.basic.constant.HttpStatus.ERROR;

@RestController
public class MyErrorController extends BasicErrorController {

    public MyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        // 设置一下信息，在application.properties中配置不生效，应该是和上面的构造方法传的有关，先这么配置，待研究
        getErrorProperties().setIncludeException(true);
        getErrorProperties().setIncludeMessage(ErrorProperties.IncludeAttribute.ALWAYS);
        getErrorProperties().setIncludeStacktrace(ErrorProperties.IncludeStacktrace.ALWAYS);
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        // 请求是成功的，碰到服务器了，只是你返回的code是对应的code
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> map;
        String exception = (String) body.get("exception");
        System.out.println(exception);
        // 设置 shiro 验证失败时的请求
        if ("org.apache.shiro.authc.AuthenticationException".equals(exception)) {
            map = Result.error(AUTH_ERROR.getCode(), AUTH_ERROR.getMsg()).toMap();
        }else {
            map = Result.error(ERROR.getCode(), ERROR.getMsg()).toMap();
        }
        return new ResponseEntity<>(map, status);
    }
}