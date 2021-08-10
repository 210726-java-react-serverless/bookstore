package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import com.revature.bookstore.web.dtos.Principal;
import com.revature.bookstore.web.util.HandlerRequest;
import com.revature.bookstore.web.util.HandlerResponse;

import java.io.IOException;

public class UserHandler extends GenericHandler {

    private final UserService userService;

    public UserHandler(ObjectMapper mapper, UserService userService) {
        super("/users", mapper);
        this.userService = userService;
    }

    @Override
    protected void doPost(HandlerRequest request, HandlerResponse response) throws IOException {
        response.setContentType("application/json");

        try {
            AppUser newUser = mapper.readValue(request.getBody(), AppUser.class);
            Principal principal = new Principal(this.userService.register(newUser));
            String payload = mapper.writeValueAsString(principal);
            response.setStatusCode(201);
            response.write(payload);
        } catch (ResourcePersistenceException rpe) {
            errorResponse(response, 409, rpe);
        } catch (JsonProcessingException jpe) {
            errorResponse(response, 400, jpe);
        }
    }

}
