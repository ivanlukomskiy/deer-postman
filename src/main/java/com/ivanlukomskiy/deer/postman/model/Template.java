package com.ivanlukomskiy.deer.postman.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String channel;
    @Lob
    private String content;
}