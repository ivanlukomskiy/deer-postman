package com.ivanlukomskiy.deer.postman.controller;

import com.ivanlukomskiy.deer.postman.model.dto.AuthDto;
import com.ivanlukomskiy.deer.postman.security.TokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@CrossOrigin
@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @RequestMapping(path = {"auth"}, method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public TokenDto authorize(@RequestBody AuthDto body) {
        return new TokenDto(tokenService.getToken(body.getLogin(), body.getPassword()));
    }

    @Data
    @AllArgsConstructor
    private class TokenDto {
        private String token;
    }
}
