package zhku.graduation.core;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Optional;

/**
 * @author qr
 * @date 2021/12/25 20:58
 */
@SpringBootApplication
@Slf4j
public class ZnygApp {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(ZnygApp.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse(null);
        log.info("\n----------------------------------------------------------\n" +
                "Application is running! Access URLs:\n\t" +
                "     Local: http://localhost:" + port + path + "/\n\t" +
                "  External: http://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: http://" + ip + ":" + port + path + "/swagger-ui.html/index.html#/\n" +
                "----------------------------------------------------------");
    }
}
