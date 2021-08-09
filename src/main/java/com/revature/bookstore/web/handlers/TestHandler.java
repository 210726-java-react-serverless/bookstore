package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.web.util.HandlerRequest;
import com.revature.bookstore.web.util.HandlerResponse;

import java.io.IOException;

public class TestHandler extends GenericHandler {

    public TestHandler(ObjectMapper mapper) {
        super("/test", mapper);
    }

    @Override
    protected void doGet(HandlerRequest request, HandlerResponse response) throws IOException {
        String payload = "<h1>/test works!</h1>";
        response.setStatusCode(200);
        response.setContentType("text/html");
        response.write(payload);
    }

}
