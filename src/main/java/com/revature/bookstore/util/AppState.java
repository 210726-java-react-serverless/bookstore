package com.revature.bookstore.util;

import com.revature.bookstore.repos.UserRepository;
import com.revature.bookstore.screens.*;
import com.revature.bookstore.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private boolean appRunning;
    private final ScreenRouter router;

    public AppState() {

        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        UserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);
        AppState app = this;


        router.addScreen(new WelcomeScreen(consoleReader, router, app))
              .addScreen(new LoginScreen(consoleReader, router, userService))
              .addScreen(new RegisterScreen(consoleReader, router, userService))
              .addScreen(new DashboardScreen(consoleReader, router))
              .addScreen(new UserProfileScreen(consoleReader, router));

    }

    public void startup() {
        router.navigate("/welcome");

        while (appRunning) {
            try {
                router.getCurrentScreen().render();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Switches 'appRunning' to false, ending the while loop and
    // closing the app through super's main.
    public void shutdown() {
        appRunning = false;
    }

}
