package zhku.graduation.core.config.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局设置跨域问题
 * @Author qr
 * @Date 2022/5/13-16:02
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                // OPTIONS 是为了通过预检功能
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许携带 cookie
                .allowCredentials(true)
                // 设置遇检请求的有效期
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
