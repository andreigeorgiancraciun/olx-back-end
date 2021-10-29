package com.example.olxpostman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@Controller
public class OlxPostmanApplication {

		@RequestMapping("/logged")
		@ResponseBody
		public String welcome() {
			return "loginPP";
		}

    public static void main(String[] args) {
        SpringApplication.run(OlxPostmanApplication.class, args);
    }

}
