package com.revature.bookstore.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.revature.bookstore")
@Import({AspectConfig.class, WebConfig.class, DataConfig.class})
public class AppConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
    }

}
