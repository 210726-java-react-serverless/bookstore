package com.revature.bookstore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import org.bson.Document;

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
    static Properties appProperties = new Properties();
    private static String ipAddress = appProperties.getProperty("ipAddress");
    private static int port = Integer.parseInt(appProperties.getProperty("port"));
    private static String dbName = appProperties.getProperty("dbName");
    private static String dbUsername = appProperties.getProperty("username");
    private static String dbPassword = appProperties.getProperty("password");
    private static MongoClient mongoClient;

    private static ConnectionFactory connection = new ConnectionFactory();

    private ConnectionFactory(){

    }

    public static MongoDatabase getConnection(){
        try{ MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(ipAddress, port))))
                        .credential(MongoCredential.createScramSha1Credential(dbUsername, dbName, dbPassword.toCharArray()))
                        .build());
        } catch (Exception e) {
            e.printStackTrace();
        };
        return mongoClient.getDatabase(dbName);
    }

}
