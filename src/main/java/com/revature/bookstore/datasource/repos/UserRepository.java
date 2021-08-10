package com.revature.bookstore.datasource.repos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.util.exceptions.DataSourceException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepository implements CrudRepository<AppUser> {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final MongoCollection<AppUser> usersCollection;

    public UserRepository(MongoClient mongoClient) {
        this.usersCollection = mongoClient.getDatabase("bookstore").getCollection("users", AppUser.class);
    }

    public AppUser findUserByCredentials(String username, String encryptedPassword) {

        try {

            Document queryDoc = new Document("username", username).append("password", encryptedPassword);
            return usersCollection.find(queryDoc).first();

        } catch (Exception e) {
            logger.error("An unexpected exception occurred.", e);
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public AppUser findUserByUsername(String username) {

        try {
            return usersCollection.find(new Document("username", username)).first();
        } catch (Exception e) {
            logger.error("An unexpected exception occurred.", e);
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

    // TODO implement this so that we can prevent multiple users from having the same email!
    public AppUser findUserByEmail(String email) {

        try {
            return usersCollection.find(new Document("email", email)).first();
        } catch (Exception e) {
            logger.error("An unexpected exception occurred.", e);
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

    @Override
    public AppUser findById(String id) {

        try {

            Document queryDoc = new Document("_id", id);
            return usersCollection.find(queryDoc).first();

        } catch (Exception e) {
            logger.error("An unexpected exception occurred.", e);
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

    @Override
    public AppUser save(AppUser newUser) {


        try {

            newUser.setId(new ObjectId().toString());
            usersCollection.insertOne(newUser);

            return newUser;

        } catch (Exception e) {
            logger.error("An unexpected exception occurred.", e);
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
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
