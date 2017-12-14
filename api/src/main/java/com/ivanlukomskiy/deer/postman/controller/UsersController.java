package com.ivanlukomskiy.deer.postman.controller;

import com.ivanlukomskiy.deer.postman.annotations.Access;
import com.ivanlukomskiy.deer.postman.model.User;
import com.ivanlukomskiy.deer.postman.model.dto.UserResponseDto;
import com.ivanlukomskiy.deer.postman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.ivanlukomskiy.deer.postman.model.User.Role.ADMIN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 15.12.2017.
 */
@CrossOrigin
@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @Access(ADMIN)
    @RequestMapping(value = "users",method = RequestMethod.GET,produces = APPLICATION_JSON_VALUE)
    public List<UserResponseDto> getUsers() {
        return userService.getUsers().stream().map(UserResponseDto::of).collect(Collectors.toList());
    }
}
