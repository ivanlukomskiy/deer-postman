package com.ivanlukomskiy.deer.postman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@ControllerAdvice
public class ExceptionsController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        logger.error("Failed to process request; exception: {}, message: {}", e.getClass(), e.getMessage(),e);
        return e.getMessage();
    }
}
