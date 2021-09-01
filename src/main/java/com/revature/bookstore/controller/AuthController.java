package com.revature.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.dtos.Credentials;
import com.revature.bookstore.dtos.ErrorResponse;
import com.revature.bookstore.dtos.Principal;
import com.revature.bookstore.web.util.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping("/auth")
public class AuthController extends HttpServlet {


    private final UserService userService;
    private final ObjectMapper mapper;
    private final TokenGenerator tokenGenerator;


    @Autowired
    public AuthController(UserService userService, ObjectMapper mapper, TokenGenerator tokenGenerator) {
        this.userService = userService;
        this.mapper = mapper;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Credentials authenticate(@RequestBody Credentials credentials, HttpServletResponse resp) throws Exception {

        Principal principal = userService.login(credentials.getUsername(), credentials.getPassword());

        String token = tokenGenerator.createToken(principal);
        resp.setHeader(tokenGenerator.gfig().getHeader(), token);

        resp.setHeader("Authorization", "Bearer " + token);

        return credentials;
    }
}
