package com.revature.bookstore.screens;

import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private final Logger logger = LogManager.getLogger(RegisterScreen.class);
    private final UserService userService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        System.out.println("\nUser Registration\n" +
                           "1) Register\n" +
                           "2) Go Back");
        System.out.print("> ");
        int userChoice = Integer.parseInt(consoleReader.readLine());

        switch (userChoice) {
            case 1:
                break;
            case 2:
                router.goToPrevious();
                return;
        }

        System.out.println("\nRegister for a new account!");

        System.out.print("First name: ");
        String firstName = consoleReader.readLine();

        System.out.print("Last name: ");
        String lastName = consoleReader.readLine();

        System.out.print("Email: ");
        String email = consoleReader.readLine();

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        AppUser newUser = new AppUser(firstName, lastName, email, username, password);

        try {
            userService.register(newUser);
            logger.info("User successfully registered!");
            router.navigate("/dashboard");
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("User not registered!");
            router.navigate("/welcome");
        }

    }

}
