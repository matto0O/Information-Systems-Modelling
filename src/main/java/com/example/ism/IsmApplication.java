package com.example.ism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.ism", "com.example.api"})
@SpringBootApplication
public class IsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsmApplication.class, args);
    }

}
