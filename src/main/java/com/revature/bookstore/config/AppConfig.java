package com.revature.bookstore.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@Import({AspectConfig.class, WebConfig.class, DataConfig.class})
public class AppConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
    }

}
