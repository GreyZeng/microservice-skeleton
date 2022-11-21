package git.snippets.skeleton.reigister.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterServerApplication.class, args);
    }
}