package com.ivanlukomskiy.deer.postman.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
@Data
@Entity
public class Dispatch {

    public Dispatch(User user) {
        this.user = user;
        createDate = new Date();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "persons_set")
    private PersonsSet personsSet;

    @ManyToOne
    private Template template;

    @ManyToOne
    @JoinColumn(name = "priorities_config")
    private PrioritiesConfig prioritiesConfig;

    private Date createDate;

    @ManyToOne
    private User user;
}
