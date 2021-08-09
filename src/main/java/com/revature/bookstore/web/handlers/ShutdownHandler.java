package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ShutdownHandler extends GenericHandler {

    public ShutdownHandler(ObjectMapper mapper) {
        super("/shutdown", mapper);
    }

}
