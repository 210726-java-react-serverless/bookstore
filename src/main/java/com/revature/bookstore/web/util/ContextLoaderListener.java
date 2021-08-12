package com.revature.bookstore.web.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.MongoClient;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.datasource.util.MongoClientFactory;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.PasswordUtils;
import com.revature.bookstore.web.filters.CorsFilter;
import com.revature.bookstore.web.servlets.AuthServlet;
import com.revature.bookstore.web.servlets.UserServlet;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("It's aliiiiive!!!");

        MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
        PasswordUtils passwordUtils = new PasswordUtils();
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        UserRepository userRepo = new UserRepository(mongoClient);
        UserService userService = new UserService(userRepo, passwordUtils);

        UserServlet userServlet = new UserServlet(userService, mapper);
        AuthServlet authServlet = new AuthServlet(userService, mapper);

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("UserServlet", userServlet).addMapping("/users/*");
        servletContext.addServlet("AuthServlet", authServlet).addMapping("/auth");

        configureLogback(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Goodbye, cruel world!!!");
        MongoClientFactory.getInstance().cleanUp();
    }

    private void configureLogback(ServletContext servletContext) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator logbackConfig = new JoranConfigurator();
        logbackConfig.setContext(loggerContext);
        loggerContext.reset();

        String logbackConfigFilePath = servletContext.getRealPath("") + File.separator + servletContext.getInitParameter("logback-config");

        try {
            logbackConfig.doConfigure(logbackConfigFilePath);
        } catch (JoranException e) {
            e.printStackTrace();
            System.out.println("An unexpected exception occurred. Unable to configure Logback.");
        }

    }

}
