package com.ivanlukomskiy.deer.postman.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivanlukomskiy.deer.postman.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 15.12.2017.
 */
@Data
@AllArgsConstructor
public class UserResponseDto {
    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getLogin(), user.getRegisterDate().getTime(), user.getRole());
    }

    private String login;
    @JsonProperty("register_date")
    private long registerDate;
    private User.Role role;
}
