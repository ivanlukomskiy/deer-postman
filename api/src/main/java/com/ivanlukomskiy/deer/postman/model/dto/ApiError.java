package com.ivanlukomskiy.deer.postman.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 16.12.2017.
 */
@Data
@AllArgsConstructor
public class ApiError {
    private String key;
}
