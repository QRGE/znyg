package zhku.graduation.core.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qr
 * @date 2022/2/25 10:58
 */
@WebFilter(value = "/*", filterName = "global-filter")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 测试过滤器
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:10000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 设置允许的请求头
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,X-Access-Token");
        // 用过滤器链,去进入到下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
