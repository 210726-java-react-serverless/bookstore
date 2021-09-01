package com.revature.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/health")
public class HealthCheckController extends HttpServlet {



    @GetMapping(produces = "application/json")
    public @ResponseBody String healthCheck(HttpServletResponse resp) {
        resp.setStatus(200);
        return "Status: UP!";
    }

}