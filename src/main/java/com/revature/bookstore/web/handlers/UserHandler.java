package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.services.UserService;

public class UserHandler extends GenericHandler {

    private final UserService userService;

    public UserHandler(ObjectMapper mapper, UserService userService) {
        super("/users", mapper);
        this.userService = userService;
    }

}
