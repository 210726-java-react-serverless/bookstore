package com.revature.bookstore.screens;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.util.AppState;
import com.revature.bookstore.util.ScreenRouter;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;

public class DashboardScreen  extends Screen {


    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
    }

    /*
        TODO
         Implement a dashboard that displays the user's username and gives them the option
         to navigate to other screens (e.g. UserProfileScreen).
     */

    @Override
    public void render() throws Exception {

        String username = ScreenRouter.getUsername();
        System.out.println("Welcome to the Dashboard Screen, " + username + "!");

        String menu = "\n1) Show/Edit User Profile.\n2) Logout.\n>";
        System.out.print(menu);

        String userInput = "";

        do{
            userInput = consoleReader.readLine();
            switch(userInput) {
                case "1":
                    router.navigate("/profile");
                    break;
                case "2":
                    router.navigate("/welcome");
                    break;
                default:
                    System.out.println("Your input was invalid. Please try again");
                    System.out.println(menu);
            }
        } while (!(userInput.equals("1") && !(userInput.equals("2"))));

    }

}
