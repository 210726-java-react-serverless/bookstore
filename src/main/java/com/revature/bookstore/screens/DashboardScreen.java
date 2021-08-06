package com.revature.bookstore.screens;

import com.revature.bookstore.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen  extends Screen {

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
    }


    /*
        TODO
         Implement a dashboard that displays the user's username and gives them the option
         to navigate to other screens (e.g. UserProfileScreen).
     */
    /*@Override
    public void render() throws Exception {
        System.out.println("DashboardScreen works!");
        System.out.println("Screen under construction, sending you back to the Welcome Screen.");
        router.navigate("/welcome");
    }*/

    @Override
    public void render() throws Exception {
        //used functional printing to include username in the welcoming message
        System.out.println("Dashboard");
        System.out.println("Hello " + router.username + ", What task would you like to complete next:\n");
        //Screen options that they can choose from
        System.out.println("1- User Profile Screen");
        System.out.println("2 -Welcome Screen");

        String userInput = consoleReader.readLine();

        if(userInput.equals("1")){
            router.navigate("/profile");
        } else if (userInput.equals("2")){
            router.navigate("/welcome");
        }
    }

}

