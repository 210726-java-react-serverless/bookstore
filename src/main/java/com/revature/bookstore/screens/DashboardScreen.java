package com.revature.bookstore.screens;

import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen  extends Screen {

    private final UserService userService;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        // Receive a user from the UserService
        AppUser user = userService.receiveUser();

        System.out.println("Welcome to the Dashboard, " + user.getUsername() + "!\n");

        System.out.print("\nYou are currently logged in. What would you like to do?"
                    + "\n1) Check or change your user profile"
                    + "\n2) Logout"
                    + "\n> ");

        String input = consoleReader.readLine();

        switch(input) {
            case "1":
                System.out.println("Understood. Navigating you to your user profile portal...");
                router.navigate("/userprofile"); // TODO: Make this match the completed UserProfileScreen route
                break;
            case "2":
                System.out.println("Understandable. Have a nice day!");
                router.navigate("/welcome");
                break;
            default:
                System.out.println("Sorry! We didn't quite catch that! Can you please try again?");
        }
    }
}
