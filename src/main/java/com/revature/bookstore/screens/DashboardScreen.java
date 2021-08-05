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
    @Override
    public void render() throws Exception {
        System.out.println("DashboardScreen works!");
        System.out.println("Screen under construction, sending you back to the Welcome Screen.");
        router.navigate("/welcome");
    }

}
