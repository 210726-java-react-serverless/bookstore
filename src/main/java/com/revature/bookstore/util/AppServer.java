package com.revature.bookstore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.datasource.util.MongoClientFactory;
import com.revature.bookstore.web.filter.CorsFilter;
import com.revature.bookstore.web.handlers.*;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.services.UserService;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class AppServer {

    private static final AppServer APP_SERVER = new AppServer();

    private HttpServer webServer;
    private List<CustomHandler> webHandlers;

    public AppServer() {

        try {

            webServer = HttpServer.create(new InetSocketAddress(8080), 0);
            webServer.setExecutor(Executors.newFixedThreadPool(10));

            ObjectMapper mapper = new ObjectMapper();
            UserRepository userRepo = new UserRepository();
            UserService userService = new UserService(userRepo);

            CorsFilter corsFilter = new CorsFilter();

            webHandlers = Arrays.asList(
                                        new TestHandler(mapper),
                                        new ShutdownHandler(mapper),
                                        new UserHandler(mapper, userService),
                                        new AuthHandler(mapper, userService)
                                       );
            webHandlers.forEach(handler -> {
                HttpContext context = webServer.createContext(handler.getMapping(), handler);
                context.getFilters().add(corsFilter);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startup() {
        webServer.start();
        System.out.println("Application server started and listening at http://localhost:8080");
        System.out.println("Handlers available at: ");
        webHandlers.forEach(handler -> System.out.println("\t- http://localhost:8080" + handler.getMapping()));
    }

    public void shutdown() {
        System.out.println("Shutting down application server.");
        MongoClientFactory.getInstance().cleanUp();
        webServer.stop(5);
    }

    public static AppServer getInstance() {
        return APP_SERVER;
    }

}
