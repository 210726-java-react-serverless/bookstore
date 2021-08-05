package com.revature.bookstore.repos;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.documents.AppUser;

import com.revature.bookstore.util.MongoClientFactory;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import org.bson.Document;

import java.io.FileReader;

import java.util.Arrays;
import java.util.Properties;

public class UserRepository implements CrudRepository<AppUser> {

    public AppUser findUserByCredentials(String username, String password) {

        try (MongoClient mongoClient = MongoClientFactory.getInstance().getMongoClient()) {

            MongoDatabase bookstoreDatabase = mongoClient.getDatabase("bookstore");
            MongoCollection<Document> usersCollection = bookstoreDatabase.getCollection("users");
            Document queryDoc = new Document("username", username).append("password", password);
            Document authUserDoc = usersCollection.find(queryDoc).first();

            if (authUserDoc == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            AppUser authUser = mapper.readValue(authUserDoc.toJson(), AppUser.class);
            authUser.setId(authUserDoc.get("_id").toString());
            System.out.println(authUser);
            return authUser;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    // TODO implement this so that we can prevent multiple users from having the same username!
    public AppUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(int id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {

        try (MongoClient mongoClient = MongoClientFactory.getInstance().getMongoClient()) {

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
