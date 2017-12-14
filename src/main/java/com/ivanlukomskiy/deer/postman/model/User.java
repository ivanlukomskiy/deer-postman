package com.ivanlukomskiy.deer.postman.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String login;

    private String passwordHash;

    private String email;

    private Date registerDate;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Status {
        ACTIVE, DELETED
    }

    public enum Role {
        ADMIN, USER
    }
}
