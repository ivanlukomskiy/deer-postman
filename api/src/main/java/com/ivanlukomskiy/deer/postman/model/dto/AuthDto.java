package com.ivanlukomskiy.deer.postman.model.dto;

import lombok.Data;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Data
public class AuthDto {
    private String login;
    private String password;
}
