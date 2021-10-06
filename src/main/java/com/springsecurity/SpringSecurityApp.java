package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringSecurityApp {

    @Bean
    public RestTemplate getResttemplate()
    {
        return new RestTemplate();
    }


    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityApp.class);
    }
}
