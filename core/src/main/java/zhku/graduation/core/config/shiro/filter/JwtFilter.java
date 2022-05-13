package zhku.graduation.core.config.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import zhku.graduation.basic.constant.Constant;
import zhku.graduation.basic.constant.HttpStatus;
import zhku.graduation.core.config.shiro.JwtToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qr
 * @date 2022/1/12 09:50
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private boolean allowOrigin = true;

    public JwtFilter(){}

    public JwtFilter(boolean allowOrigin){
        this.allowOrigin = allowOrigin;
    }

    /**
     * 执行登录认证
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        executeLogin(request, response);
        // return true will allow access
        return true;
//        try {
//
//        } catch (Exception e) {
//            throw new InValidTokenException("Token 无效");
//        }
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constant.TOKEN);
        if(token == null){
            token = httpServletRequest.getParameter(Constant.TOKEN);
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(allowOrigin){
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            // token 请求头
            httpServletResponse.setHeader("Access-Control-Allow-Headers", Constant.TOKEN);
            // 是否允许发送Cookie，默认Cookie不包括在CORS请求之中。设为true时，表示服务器允许Cookie包含在请求中。
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }
        // 跨域时会首先发送一个 option 请求进行预检，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.SUCCESS.getCode());
            return false;
        }
        return super.preHandle(request, response);
    }
}
