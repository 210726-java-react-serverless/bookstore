package com.revature.bookstore.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourceNotFoundException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import com.revature.bookstore.web.dtos.AppUserDTO;
import com.revature.bookstore.web.dtos.ErrorResponse;
import com.revature.bookstore.web.dtos.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserServlet {

    private final Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private final UserService userService;
    private final ObjectMapper mapper;

    @Autowired
    public UserServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    /**
     * Handles GET requests to "/users/*" and will determine if the request is a simple availability check
     * (see {@link #availabilityCheck(HttpServletRequest, HttpServletResponse)}) or a resource retrieval.
     * If the request is for resource retrieval, security checks will ensure that the requesting user is both
     * authenticated and authorized to perform the action.
     *
     * @param req The incoming request from the client.
     * @param resp The outgoing response intended for the client.
     * @throws IOException Occurs if there are issues with writing to the response body
     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        PrintWriter respWriter = resp.getWriter();
//        resp.setContentType("application/json");
//
//        //------------------------------------------------------------------------------------------
//
//        /*
//            This segment checks to see if the requester is trying to check the availability of a username
//            or email address. We do this by checking to see if "/availability" was included on the end
//            of the request URI.
//         */
//
//        String[] reqFrags = req.getRequestURI().split("/");
//        boolean checkingAvailability = reqFrags[reqFrags.length - 1].equals("availability");
//
//        if (checkingAvailability) {
//            availabilityCheck(req, resp);
//            return; // end here, do not proceed to the remainder of the method's logic
//        }
//
//        //------------------------------------------------------------------------------------------
//
//        /*
//            This segment checks to see if the requesting user is authenticated and has the proper
//            permissions to perform the action. This logic is what secures our endpoint and prevents
//            unauthorized access and usage.
//         */
//
//        // Get the principal information from the request, if it exists.
//        Principal requestingUser = (Principal) req.getAttribute("principal");
//
//        // Check to see if there was a valid principal attribute
//        if (requestingUser == null) {
//            String msg = "No session found, please login.";
//            logger.info(msg);
//            writeErrorResponse(msg, 401, resp);
//            return; // end here, do not proceed to the remainder of the method's logic
//        } else if (!requestingUser.getUsername().equals("wsingleton")) {
//            String msg = "Unauthorized attempt to access endpoint made by: " + requestingUser.getUsername();
//            writeErrorResponse(msg, 403, resp);
//            logger.info(msg);
//            return; // end here, do not proceed to the remainder of the method's logic
//        }
//
//        //------------------------------------------------------------------------------------------
//
//        /*
//            This segment is what performs the searching logic for retrieving users based on the request.
//            If the user included a request parameter called "id", we will simply search the data source
//            for a single user with the provided id. Otherwise, we will assume that they intend to retrieve
//            all the users from the data source.
//         */
//
//        String userIdParam = req.getParameter("id");
//
//        try {
//
//            if (userIdParam == null) {
//                List<AppUserDTO> users = userService.findAll();
//                respWriter.write(mapper.writeValueAsString(users));
//            } else {
//                AppUserDTO user = userService.findUserById(userIdParam);
//                respWriter.write(mapper.writeValueAsString(user));
//            }
//
//        } catch (ResourceNotFoundException rnfe) {
//            logger.info(rnfe.getMessage());
//            writeErrorResponse(rnfe.getMessage(), 404, resp);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            writeErrorResponse("An unexpected error occurred on the server.", 500, resp);
//        }
//
//        //------------------------------------------------------------------------------------------
//
//    }

    /**
     * Handles POST requests made to "/users/*" and attempts to register the user (provided in the
     * request body) to the data source. If there are any issues with persistence an appropriate
     * error response message will be sent to notify the requester. Otherwise, this method will return
     * a 201 status code and a Principal object that includes basic user information.
     *
     * @param req The incoming request from the client.
     * @param resp The outgoing response intended for the client.
     * @throws IOException Occurs if there are issues with writing to the response body
     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        System.out.println(req.getAttribute("filtered"));
//        PrintWriter respWriter = resp.getWriter();
//        resp.setContentType("application/json");
//
//        try {
//
//            AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
//            Principal principal = new Principal(userService.register(newUser));
//            String payload = mapper.writeValueAsString(principal);
//            respWriter.write(payload);
//            resp.setStatus(201);
//
//        } catch (InvalidRequestException | MismatchedInputException e) {
//            logger.info(e.getMessage());
//            writeErrorResponse(e.getMessage(), 400, resp);
//        } catch (ResourcePersistenceException rpe) {
//            logger.info(rpe.getMessage());
//            writeErrorResponse(rpe.getMessage(), 409, resp);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            writeErrorResponse("An unexpected error occurred on the server.", 500, resp);
//        }
//
//
//    }
//
//    /**
//     * A convenience method used to abstract logic related to creating and writing a ErrorResponse to
//     * the response body that will be sent back to the requester.
//     *
//     * @param msg Descriptive error message that will be sent to the requester
//     * @param statusCode HTTP status code that will be set on the response
//     * @param resp HttpServletResponse object that is used to set the status code and write to the body of the response
//     * @throws IOException Occurs if there are issues with writing to the response body
//     */
//    public void writeErrorResponse(String msg, int statusCode, HttpServletResponse resp) throws IOException {
//        resp.setStatus(statusCode);
//        ErrorResponse errResp = new ErrorResponse(statusCode, msg);
//        resp.getWriter().write(mapper.writeValueAsString(errResp));
//    }
//
//    /**
//     * Used to determine if a provided username or email is available for use. An appropriate response is sent
//     * back to indicate whether the provided value is already used by another user in the data source.
//     * @param req
//     * @param resp
//     * @throws IOException
//     */
//    public void availabilityCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        String usernameParam = req.getParameter("username");
//        String emailParam = req.getParameter("email");
//        boolean isAvailable;
//
//        try {
//
//            if (usernameParam != null) {
//                isAvailable = userService.isUsernameAvailable(usernameParam);
//            } else if (emailParam != null) {
//                isAvailable = userService.isEmailAvailable(emailParam);
//            } else {
//                writeErrorResponse("No values provided, cannot check availability.", 400, resp);
//                return;
//            }
//
//            if (isAvailable) {
//                resp.setStatus(200);
//                resp.getWriter().write("{\"isAvailable\": \"true\"}");
//            } else {
//                resp.setStatus(409);
//                resp.getWriter().write("{\"isAvailable\": \"false\"}");
//            }
//
//        } catch (InvalidRequestException ire) {
//            logger.info(ire.getMessage());
//            writeErrorResponse(ire.getMessage(), 400, resp);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            writeErrorResponse("An unexpected error occurred on the server.", 500, resp);
//        }
//
//    }
//
}
