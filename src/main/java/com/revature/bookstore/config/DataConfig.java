package com.revature.bookstore.config;

import com.mongodb.client.MongoClient;
import com.revature.bookstore.datasource.util.MongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.revature.bookstore.datasource.repos")
public class DataConfig {

//    @Bean
//    public MongoClient mongoClient() {
//        return mongoClientFactory().getConnection();
//    }
//
//    @Bean
//    public MongoClientFactory mongoClientFactory() {
//        return new MongoClientFactory();
//    }

}
