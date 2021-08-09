package com.revature.bookstore.web.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class HandlerRequest {

    private final HttpExchange exchange;
    private final String body;

    public HandlerRequest(HttpExchange exchange) {
        this.exchange = exchange;
        this.body  = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public String getPath() {
        return exchange.getRequestURI().getPath();
    }

    public String getRawQuery() {
        return exchange.getRequestURI().getRawQuery();
    }

    public List<String> getHeaderValues(String headerKey) {
        return exchange.getRequestHeaders().get(headerKey);
    }

    public Object getAttribute(String key) {
        return exchange.getHttpContext().getAttributes().get(key);
    }

    public void addAttribute(String key, Object value) {
        exchange.getHttpContext().getAttributes().put(key, value);
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HandlerRequest{\n" +
                "\tmethod='" + getMethod() + "',\n" +
                "\tpath='" + getPath() + "',\n" +
                "\trawQuery='" + getRawQuery() + "',\n" +
                "\theaders=" + exchange.getRequestHeaders().entrySet() + ",\n" +
                "\tattributes=" + exchange.getHttpContext().getAttributes().entrySet() + ",\n" +
                ", body=" + getBody() +
                '}';
    }

}
