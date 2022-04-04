package zhku.graduation.core.modules.socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author qr
 * @date 2022/2/14 11:52
 */
@Configuration
public class WebSocketConfig {

    /**
     * 自动注册使用了 @ServerEndpoint 注解声明的对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
