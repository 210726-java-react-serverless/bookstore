package com.revature.bookstore.util;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;

public class ConnectionFactory {

    private final static ConnectionFactory connectionFactory = new ConnectionFactory();
    private final Properties props = new Properties();

    private ConnectionFactory() {
        getProperties();
    }

    public static ConnectionFactory getInstance() {
        return connectionFactory;
    }

    public MongoClient getClient() {
        String ipAddress = props.getProperty("ipAddress");
        int port = Integer.parseInt(props.getProperty("port"));
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String dbName = props.getProperty("dbName");

        try {
            MongoClient client = MongoClients.create(
                    MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(ipAddress, port))))
                        .credential(MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray()))
                        .build()
            );
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Unable to load properties file.");
        }
    }

    private void getProperties() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Unable to load properties file.");
        }
    }
}
