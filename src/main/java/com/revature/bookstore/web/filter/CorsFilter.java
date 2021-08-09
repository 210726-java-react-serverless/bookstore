package com.revature.bookstore.web.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CorsFilter extends Filter {

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        exchange.getResponseHeaders().add("Access-Control-Expose-Headers", "Authorization");
        exchange.getResponseHeaders().add("Access-Control-Expose-Headers", "Authorization, Content-Type");
        exchange.setAttribute("cors-filtered", "true");
        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return "Writes basic CORS headers to all responses.";
    }

}
