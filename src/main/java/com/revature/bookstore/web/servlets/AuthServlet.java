package com.revature.bookstore.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.web.dtos.Credentials;
import com.revature.bookstore.web.dtos.ErrorResponse;
import com.revature.bookstore.web.dtos.Principal;
import com.revature.bookstore.web.util.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/auth")
public class AuthServlet {

    private final UserService userService;
    private final ObjectMapper mapper;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public AuthServlet(UserService userService, ObjectMapper mapper, TokenGenerator tokenGenerator) {
        this.userService = userService;
        this.mapper = mapper;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping(value="/login",consumes = "application/json")
    public @ResponseBody String authenticateUser(@RequestBody Credentials creds, HttpServletResponse resp) {
        Principal principal = userService.login(creds.getUsername(), creds.getPassword());
        String token = tokenGenerator.createToken(principal);
        resp.setHeader(tokenGenerator.getJwtConfig().getHeader(), token);
        return principal.toString();
    }
}
