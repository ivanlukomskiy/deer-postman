package com.ivanlukomskiy.deer.postman.controller;

import com.ivanlukomskiy.deer.postman.DeerSantaException;
import com.ivanlukomskiy.deer.postman.model.dto.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsController.class);

    private Properties errorsCodesMap = new Properties();

    @PostConstruct
    public void init() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("error-codes-map.properties")) {
            errorsCodesMap.load(is);
        } catch (IOException e) {
            logger.error("Failed to load error codes");
        }
    }

    @ExceptionHandler(DeerSantaException.class)
    public ResponseEntity<Object> handle(DeerSantaException e) {
        int errorCode = 500;
        if (errorsCodesMap.containsKey(e.getMessage())) {
            errorCode = Integer.valueOf(errorsCodesMap.getProperty(e.getMessage()));
        }
        ApiError apiError = new ApiError(e.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception e) {
        logger.error("Unexpected error",e);
        ApiError apiError = new ApiError("unexpected");
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}