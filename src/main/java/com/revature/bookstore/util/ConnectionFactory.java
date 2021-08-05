package com.revature.bookstore.util;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;

/*
    TODO
     Implement a singleton factory that will supply DAO classes
     with a connection to the data source for performing CRUD
     operations.
 */
public class ConnectionFactory {

    public static MongoDatabase createConnection(){
        Properties appProperties = new Properties();

        try {
            appProperties.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Unable to load properties file.");
        }

        String ipAddress = appProperties.getProperty("ipAddress");
        int port = Integer.parseInt(appProperties.getProperty("port"));
        String dbName = appProperties.getProperty("dbName");
        String dbUsername = appProperties.getProperty("username");
        String dbPassword = appProperties.getProperty("password");

        // TODO obfuscate DB credentials
        // TODO abstract connection logic from here
        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(ipAddress, port))))
                        .credential(MongoCredential.createScramSha1Credential(dbUsername, dbName, dbPassword.toCharArray()))
                        .build()
        )) {

            MongoDatabase bookstoreDatabase = mongoClient.getDatabase("bookstore");

            return bookstoreDatabase;
        }

    }

}
