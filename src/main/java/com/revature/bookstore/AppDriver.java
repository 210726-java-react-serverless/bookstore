package com.revature.bookstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.revature.bookstore")
@PropertySource("classpath:application.properties")
@Import({AspectConfig.class, WebConfig.class, DataConfig.class})
public class AppConfig {


}
