package com.revature.bookstore.web.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class HandlerResponse {

    private final HttpExchange exchange;
    private int statusCode;

    public HandlerResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public OutputStream getOutputStream() {
        return exchange.getResponseBody();
    }

    public void write(String payload) throws IOException {
        if (statusCode == 0) setStatusCode(200);
        if (getContentType() == null) setContentType("text/plain");
        exchange.sendResponseHeaders(statusCode, payload.length());
        exchange.getResponseBody().write(payload.getBytes());
    }

    public Map<String, List<String>> getHeaders() {
        return exchange.getResponseHeaders();
    }

    public List<String> getHeaderValues(String headerKey) {
        return exchange.getResponseHeaders().get(headerKey);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getContentType() {
        return exchange.getResponseHeaders().get("Content-type");
    }

    public void setContentType(String contentType) {
        exchange.getResponseHeaders().set("Content-type", contentType);
    }

    @Override
    public String toString() {
        return "HandlerResponse{\n" +
                "\theaders=" + exchange.getResponseHeaders().entrySet() + ",\n" +
                "\tstatusCode=" + statusCode + ",\n" +
                "\tcontentType=" + getContentType() + "\n" +
                '}';
    }
}
