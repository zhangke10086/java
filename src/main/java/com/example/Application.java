package com.example;

import com.example.common.login.entity.LoginUser;
import com.example.common.resformat.JwtHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableSwagger2
@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
