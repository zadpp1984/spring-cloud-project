package com.wzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableFeignClients(
        {"com.wzy.role"
                ,"com.wzy.user"
                ,"com.wzy.redis"})
public class StartSSOService {
    public static void main(String[] args) {
        SpringApplication.run(StartSSOService.class, args);
    }
}