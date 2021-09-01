package com.revature.bookstore.util;

import com.revature.bookstore.dtos.ErrorResponse;
import com.revature.bookstore.util.exceptions.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseAspect {

    Logger logger = LoggerFactory.getLogger(ErrorResponseAspect.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(InvalidRequestException e) {
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleResourcePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAuthenticationException(AuthenticationException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataSourceException(DataSourceException e) {
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOtherExceptions(Exception e) {
        return new ErrorResponse(500, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse somethingsomethingExceptionsorWhatever(Exception E){
        return new ErrorResponse((503), E.getMessage());
    }


}
