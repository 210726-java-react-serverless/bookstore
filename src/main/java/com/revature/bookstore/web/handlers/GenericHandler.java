package com.revature.bookstore.web.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.bookstore.web.dtos.ErrorResponse;
import com.revature.bookstore.web.util.HandlerRequest;
import com.revature.bookstore.web.util.HandlerResponse;

import com.sun.net.httpserver.HttpExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class GenericHandler implements CustomHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final ObjectMapper mapper;
    private final String mapping;


    public GenericHandler(String mapping, ObjectMapper mapper) {
        this.mapping = mapping;
        this.mapper = mapper;
    }

    @Override
    public String getMapping() {
        return mapping;
    }

    @Override
    public void handle(HttpExchange exchange) {

        HandlerRequest request = new HandlerRequest(exchange);
        HandlerResponse response = new HandlerResponse(exchange);

        try {
            switch (request.getMethod()) {
                case "GET":
                    doGet(request, response);
                    break;
                case "POST":
                    doPost(request, response);
                    break;
                case "PUT":
                    doPut(request, response);
                    break;
                case "PATCH":
                    doPatch(request, response);
                    break;
                case "DELETE":
                    doDelete(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            handleUncaughtExceptions(response);
        }

        exchange.close();
    }

    protected void doGet(HandlerRequest request, HandlerResponse response) throws IOException {
        methodNotAllowed(response);
    }

    protected void doPost(HandlerRequest request, HandlerResponse response) throws IOException {
        methodNotAllowed(response);
    }

    protected void doPut(HandlerRequest request, HandlerResponse response) throws IOException {
        methodNotAllowed(response);
    }

    protected void doPatch(HandlerRequest request, HandlerResponse response) throws IOException {
        methodNotAllowed(response);
    }

    protected void doDelete(HandlerRequest request, HandlerResponse response) throws IOException {
        methodNotAllowed(response);
    }

    protected void errorResponse(HandlerResponse response, int statusCode, Throwable e) {
        try {
            response.setStatusCode(statusCode);
            String payload = mapper.writeValueAsString(new ErrorResponse(response.getStatusCode(), e.getMessage()));
            response.write(payload);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void methodNotAllowed(HandlerResponse response) {

        String payload = "<h1>405 - Method Not Allowed</h1>\n" +
                         "<h2>The HTTP method used is not supported by this endpoint</h2>";

        response.setStatusCode(405);
        response.setContentType("text/html");

        try {
            response.write(payload);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private void handleUncaughtExceptions(HandlerResponse response) {

        response.setContentType("text/html");
        response.setStatusCode(500);
        String payload = "<h1>500 - Internal Server Error</h1>" +
                         "<h2>It's not you, it's us.</h2>";

        try {
            response.write(payload);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }

}
