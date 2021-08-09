package com.revature.bookstore.web.handlers;

import com.sun.net.httpserver.HttpHandler;

public interface CustomHandler extends HttpHandler {
    String getMapping();
}
