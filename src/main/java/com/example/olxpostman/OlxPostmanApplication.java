package com.example.olxpostman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OlxPostmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlxPostmanApplication.class, args);
    }
}
