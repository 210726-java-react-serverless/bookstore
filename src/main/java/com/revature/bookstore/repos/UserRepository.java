package com.revature.bookstore.repos;

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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class UserRepository implements CrudRepository<AppUser> {

    public AppUser findUserByCredentials(String username, String password) {
        return null;
    }

    public AppUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(int id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {

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
        String username = appProperties.getProperty("username");
        String password = appProperties.getProperty("password");

        // TODO obfuscate DB credentials
        // TODO abstract connection logic from here
        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                                   .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(ipAddress, port))))
                                   .credential(MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray()))
                                   .build()
        )) {

            MongoDatabase bookstoreDb = mongoClient.getDatabase("bookstore");
            MongoCollection<Document> usersCollection = bookstoreDb.getCollection("users");
            Document newUserDoc = new Document("firstName", newUser.getFirstName())
                                       .append("lastName", newUser.getLastName())
                                       .append("email", newUser.getEmail())
                                       .append("username", newUser.getUsername())
                                       .append("password", newUser.getPassword());

            usersCollection.insertOne(newUserDoc);
            newUser.setId(newUserDoc.get("_id").toString());
            System.out.println(newUser);

        }

        return newUser;

    }

    @Override
    public boolean update(AppUser updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

}
