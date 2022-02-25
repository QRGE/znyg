package zhku.graduation.core.config.filter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author qr
 * @date 2022/2/25 10:58
 */
@WebFilter(value = "/*", filterName = "global-filter")
public class GlobalFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 测试过滤器
        System.out.println("Global Filter");
        // 用过滤器链,去进入到下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
