package com.revature.bookstore;

import com.revature.bookstore.util.AppServer;

public class AppDriver {

    public static void main(String[] args) {

        AppServer app = AppServer.getInstance();
        app.startup();

    }

}
