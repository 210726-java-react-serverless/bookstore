package com.revature.bookstore.datasource.repos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.util.exceptions.DataSourceException;

import org.bson.Document;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements CrudRepository<AppUser> {

    private final MongoCollection<AppUser> usersCollection;

    @Autowired
    public UserRepository(MongoClient mongoClient) {
        this.usersCollection = mongoClient.getDatabase("bookstore").getCollection("users", AppUser.class);
    }

    public AppUser findUserByCredentials(String username, String encryptedPassword) {

        try {

            Document queryDoc = new Document("username", username).append("password", encryptedPassword);
            return usersCollection.find(queryDoc).first();

        } catch (Exception e) {
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public AppUser findUserByUsername(String username) {

        try {
            return usersCollection.find(new Document("username", username)).first();
        } catch (Exception e) {
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

    public AppUser findUserByEmail(String email) {

        try {
            return usersCollection.find(new Document("email", email)).first();
        } catch (Exception e) {
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

    @Override
    public List<AppUser> findAll() {

        List<AppUser> users = new ArrayList<>();

        try {
            usersCollection.find().into(users);
        } catch (Exception e) {
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

        return users;


    }

    @Override
    public AppUser findById(String id) {

        try {

            Document queryDoc = new Document("_id", id);
            return usersCollection.find(queryDoc).first();

        } catch (Exception e) {
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
