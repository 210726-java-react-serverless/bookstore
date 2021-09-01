package com.revature.bookstore.web.servlets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/health")
public class HealthCheckServlet {

    @GetMapping("/check")
    public @ResponseBody String getHealthCheck() {
        return "status: UP";
    }

}
