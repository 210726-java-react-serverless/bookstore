package com.revature.bookstore.config;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@Import(EmbeddedMongoAutoConfiguration.class)
public class LocalConfig implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public LocalConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.register(new AppUser("Wezley", "Singleton", "wezley.singleton@revature.com", "wsingleton", "revature"));
        userService.register(new AppUser("Alice", "Anderson", "alice.anderson@revature.com", "aanderson", "revature"));
        userService.register(new AppUser("Bob", "Bailey", "bob.bailey@revature.com", "bbailey", "revature"));
        userService.register(new AppUser("Charles", "Cantrell", "charles.cantrell@revature.com", "ccantrell", "revature"));
        userService.register(new AppUser("Derek", "Daniels", "derek.daniels@revature.com", "ddaniels", "revature"));
        userService.register(new AppUser("Emily", "Edwards", "emily.edwards@revature.com", "eedwards", "revature"));
        userService.register(new AppUser("Frank", "Foreman", "frank.foreman@revature.com", "fforeman", "revature"));
    }

}
