package com.revature.bookstore.screens;

import com.revature.bookstore.repos.UserRepository;
import com.revature.bookstore.util.ScreenRouter;

import java.io.BufferedReader;


public class DashboardScreen  extends Screen {

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
    }
    UserRepository user;
    /*
        TODO
         Implement a dashboard that displays the user's username and gives them the option
         to navigate to other screens (e.g. UserProfileScreen).
     */
    @Override
    public void render() throws Exception {
        //used functional printing to include username in the welcoming message
        System.out.printf("Welcome %n to the Dashboard Screen\n", user.dbUsername);
        System.out.println("Here, you can review and update your details\n");
        System.out.println("Here are your current details:\n");


        System.out.println(user.dbUsername + "What task would you like to complete next:\n");
        //Screen options that they can choose from
        System.out.println("1- User Profile Screen");
        System.out.println("2 -Welcome Screen");

        String userInput = consoleReader.readLine();

        if(userInput == "1"){
            router.navigate("/profile");
        } else if (userInput == "2"){
            router.navigate("/welcome");
        }
        router.navigate("/welcome");
    }

}
