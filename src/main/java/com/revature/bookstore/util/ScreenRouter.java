package com.revature.bookstore.util;

import com.revature.bookstore.screens.Screen;
import com.revature.bookstore.util.exceptions.ScreenNotFoundException;

import java.util.ArrayDeque;
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
    private ArrayDeque<Screen> history;

    public ScreenRouter() {
        history = new ArrayDeque<Screen>();
    }

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        if(currentScreen != null) {
            history.push(currentScreen);
        }
        currentScreen = screens.stream()
                               .filter(screen -> screen.getRoute().equals(route))
                               .findFirst()
                               .orElseThrow(ScreenNotFoundException::new);
    }

    public void goToPrevious() throws ScreenNotFoundException{
        if (history.size() == 0) { throw new ScreenNotFoundException(); }
        currentScreen = history.pop();
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public int historySize() {
        return history.size();
    }

    public boolean hasHistory() {
        return history.size() > 0;
    }

    public void clearHistory() {
        history.clear();
    }
}
