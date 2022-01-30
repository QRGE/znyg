package zhku.graduation.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qr
 * @date 2022/1/30 11:57
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                 //是否发送Cookie
                 .allowCredentials(true)
                 //放行哪些原始域
                 .allowedOrigins("*")
                 .allowedMethods("GET", "POST", "PUT", "DELETE")
                 .allowedHeaders("*")
                 .exposedHeaders("*");
    }
}
