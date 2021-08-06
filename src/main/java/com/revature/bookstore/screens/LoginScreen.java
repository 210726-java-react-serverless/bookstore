package com.revature.bookstore.screens;

import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        System.out.println("\nUser Login\n" +
                "1) Login\n" +
                "2) Go Back");
        System.out.println("> ");
        int userChoice = Integer.parseInt(consoleReader.readLine());

        switch (userChoice) {
            case 1:
                break;
            case 2:
                router.goToPrevious();
                return;
        }

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        AppUser authUser = userService.login(username, password);

        if (authUser != null) {
            System.out.printf("Login successful for user: %s!\n", authUser.getUsername());
            router.navigate("/dashboard");
        } else {
            System.out.println("No user found with provided credentials");
        }


    }

}
