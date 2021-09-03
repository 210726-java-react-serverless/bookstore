package com.revature.bookstore.web.controllers;

import com.revature.bookstore.services.UserService;
import com.revature.bookstore.web.dtos.Credentials;
import com.revature.bookstore.web.dtos.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Principal authenticate(@RequestBody @Valid Credentials credentials, HttpServletResponse resp) {
        Principal principal = userService.login(credentials.getUsername(), credentials.getPassword());
        resp.setHeader("Authorization", principal.getToken());
        return principal;
    }

}
