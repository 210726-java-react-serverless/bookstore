package com.revature.bookstore.util;

import com.revature.bookstore.screens.Screen;
import com.revature.bookstore.util.exceptions.ScreenNotFoundException;

import java.util.HashSet;
import java.util.Set;

/*
    TODO
     Implement a feature that will allow user's to go back to the previous screen
     without having to traverse through the screen set.
*/
public class ScreenRouter {

    private Screen currentScreen;
    private final Set<Screen> screens = new HashSet<>();
    public String username;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        currentScreen = screens.stream()
                               .filter(screen -> screen.getRoute().equals(route))
                               .findFirst()
                               .orElseThrow(ScreenNotFoundException::new);
    }

    public void navigate(String route, String username) {
        currentScreen = screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(ScreenNotFoundException::new);
        this.username = username;
    }


    public Screen getCurrentScreen() {
        return currentScreen;
    }

}
