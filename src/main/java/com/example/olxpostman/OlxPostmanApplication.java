package com.example.olxpostman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableFeignClients
//@EnableWebSecurity
public class OlxPostmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlxPostmanApplication.class, args);
    }
}
