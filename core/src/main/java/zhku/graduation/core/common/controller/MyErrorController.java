package zhku.graduation.core.common.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static zhku.graduation.basic.constant.HttpStatus.AUTH_ERROR;

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
        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<>();
        String exception = (String) body.get("exception");
        System.out.println(exception);
        // 设置 shiro 验证失败时的请求
        if ("org.apache.shiro.authc.AuthenticationException".equals(exception)) {
            map.put("code", AUTH_ERROR.getCode());
            map.put("message", AUTH_ERROR.getMsg());
        }else {
            map.put("code", body.get("status"));
            map.put("message", "".equals(body.get("message")) ? "系统错误" : body.get("message"));
        }
        return new ResponseEntity<>(map, status);
    }
}