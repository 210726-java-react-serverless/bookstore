package com.revature.bookstore.util;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
    TODO
     Implement a singleton factory that will supply DAO classes
     with a connection to the data source for performing CRUD
     operations.
 */
public class MongoClientFactory {

    private final static MongoClientFactory MONGO_CLIENT_FACTORY = new MongoClientFactory();
    private final Properties dbProperties = new Properties();

    private MongoClientFactory() {
        try {
            dbProperties.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Unable to load properties file.");
        }
    }

    public static MongoClientFactory getInstance() {
        return MONGO_CLIENT_FACTORY;
    }

    public MongoClient getMongoClient() {

        String ipAddress = dbProperties.getProperty("ipAddress");
        int port = Integer.parseInt(dbProperties.getProperty("port"));
        String dbName = dbProperties.getProperty("dbName");
        String dbUsername = dbProperties.getProperty("username");
        String dbPassword = dbProperties.getProperty("password");

        MongoCredential credentials = MongoCredential.createCredential(dbUsername, dbName, dbPassword.toCharArray());
        List<ServerAddress> hosts = Arrays.asList(new ServerAddress(ipAddress, port));

        MongoClientSettings settings = MongoClientSettings.builder()
                                                          .applyToClusterSettings(builder -> builder.hosts(hosts))
                                                          .credential(credentials)
                                                          .build();

        return MongoClients.create(settings);

    }





}
