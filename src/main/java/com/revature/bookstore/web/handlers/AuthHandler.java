package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.web.dtos.Credentials;
import com.revature.bookstore.web.dtos.Principal;
import com.revature.bookstore.web.util.HandlerRequest;
import com.revature.bookstore.web.util.HandlerResponse;

import java.io.IOException;

public class AuthHandler extends GenericHandler {

    private final ObjectMapper mapper;
    private final UserService userService;

    public AuthHandler(ObjectMapper mapper, UserService userService) {
        super("/auth", mapper);
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    protected void doPost(HandlerRequest request, HandlerResponse response) throws IOException {

        response.setContentType("application/json");

        try {
            Credentials creds = mapper.readValue(request.getBody(), Credentials.class);
            Principal principal = new Principal(this.userService.login(creds.getUsername(), creds.getPassword()));
            String payload = mapper.writeValueAsString(principal);
            response.setStatusCode(200);
            response.write(payload);
        } catch (AuthenticationException ae) {
            errorResponse(response, 401, ae);
        } catch (JsonProcessingException jpe) {
            errorResponse(response, 400, jpe);
        }


    }

}
