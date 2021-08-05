package com.revature.bookstore.screens;

import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.ScreenRouter;
import com.revature.bookstore.util.exceptions.AuthenticationException;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        System.out.println("\nLogin to your account!\n");

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        if(username.equals("b")){
            router.getPreviousScreen().render() ;
        }

        System.out.print("Password: ");
        String password = consoleReader.readLine();


        try {
            AppUser authUser = userService.login(username, password);
            System.out.println("Login successful!");
            router.navigate("/dashboard");
        } catch (AuthenticationException ae) {
            System.out.println("No user found with provided credentials!");
            System.out.println("Navigating back to welcome screen...");
            router.navigate("/welcome");
        }


    }

}
