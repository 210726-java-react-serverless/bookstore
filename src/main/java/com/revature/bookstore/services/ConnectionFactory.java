package com.revature.bookstore.services;

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

    private String ipAddress;
    private String username;
    private String dbName;
    private String password;
    private int port;
    private MongoClient mongoClient;
    private static ConnectionFactory connectionFactory = null;


    private ConnectionFactory(){
        Properties appProperties = new Properties();

        try {
            appProperties.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Unable to load properties file.");
        }
        ipAddress = appProperties.getProperty("ipAddress");
        port = Integer.parseInt(appProperties.getProperty("port"));
        dbName = appProperties.getProperty("dbName");
        username = appProperties.getProperty("username");
        password = appProperties.getProperty("password");

        try{
            this.mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(ipAddress, port))))
                            .credential(MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray()))
                            .build());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void cleanUp(){
        mongoClient.close();
    }
    public static ConnectionFactory getInstance(){
        if(connectionFactory==null){
            connectionFactory=new ConnectionFactory();
        }
        return connectionFactory;
    }
    public MongoClient getConnection(){
        return mongoClient;
    }
}
