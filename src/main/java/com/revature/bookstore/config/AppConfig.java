package com.revature.bookstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.revature.bookstore.util.PasswordUtils;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Collections;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@ComponentScan("com.revature.bookstore")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer {

    @Value("${ipAddress}")
    private String ipAddress;
    @Value("${port}")
    private int port;
    @Value("${dbName}")
    private String dbName;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
        container.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(container));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(container));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }

    @Bean
    public PasswordUtils passwordUtils() {
        return new PasswordUtils();
    }

    @Bean(destroyMethod = "close")
    public MongoClient mongoClient() {

        List<ServerAddress> hosts = Collections.singletonList(new ServerAddress(ipAddress, port));
        MongoCredential credentials = MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray());
        CodecRegistry defaultCodecRegistry = getDefaultCodecRegistry();
        PojoCodecProvider pojoCodecProvider= PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(defaultCodecRegistry, fromProviders(pojoCodecProvider));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(hosts))
                .credential(credentials)
                .codecRegistry(pojoCodecRegistry)
                .build();
        return MongoClients.create(settings);
    }
}
