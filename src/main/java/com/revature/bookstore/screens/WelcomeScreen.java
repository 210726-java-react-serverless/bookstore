package com.revature.bookstore.screens;

import com.revature.bookstore.util.AppState;
import com.revature.bookstore.util.ScreenRouter;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    private AppState app;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router, AppState app) {
        super("WelcomeScreen", "/welcome", consoleReader, router);
        this.app = app;
    }

    @Override
    public void render() throws Exception {

        String menu = "\nWelcome to RevaBooks!\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit application\n" +
                "> ";

        System.out.print(menu);

        String userSelection = consoleReader.readLine();

        switch (userSelection) {

            case "1":
                router.navigate("/login");
                break;
            case "2":
                router.navigate("/register");
                break;
            case "3":
                System.out.println("Exiting application...");
                app.shutdown();
                break;
            default:
                System.out.println("You provided an invalid value, please try again.");

        }

    }

}
