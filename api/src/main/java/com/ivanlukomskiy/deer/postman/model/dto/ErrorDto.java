package com.ivanlukomskiy.deer.postman.model.dto;

import lombok.Data;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Data
public class ErrorDto {

    public ErrorDto(Exception e) {
        this.exceptionClass = e.getClass().getCanonicalName();
        this.message = e.getMessage();
    }

    private String exceptionClass;
    private String message;
}
