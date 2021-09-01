package com.revature.bookstore.web.util;

import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourceNotFoundException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import com.revature.bookstore.web.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseAspect {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ExceptionHandler({InvalidRequestException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(Exception e) {
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleResourcePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Exception e) {
        return new ErrorResponse(500, e.getMessage());
    }

}
