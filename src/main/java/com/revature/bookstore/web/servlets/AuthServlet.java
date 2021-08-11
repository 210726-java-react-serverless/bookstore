package com.revature.bookstore.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.web.dtos.Credentials;
import com.revature.bookstore.web.dtos.ErrorResponse;
import com.revature.bookstore.web.dtos.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper mapper;

    public AuthServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        try {

            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            AppUser authUser = userService.login(creds.getUsername(), creds.getPassword());
            Principal principal = new Principal(authUser);
            String payload = mapper.writeValueAsString(principal);
            respWriter.write(payload);

        } catch (AuthenticationException ae) {
            resp.setStatus(401); // server's fault
            ErrorResponse errResp = new ErrorResponse(401, ae.getMessage());
            respWriter.write(mapper.writeValueAsString(errResp));
        }  catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500); // server's fault
        }

    }
}
