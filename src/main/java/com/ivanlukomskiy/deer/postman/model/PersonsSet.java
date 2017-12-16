package com.ivanlukomskiy.deer.postman.model;

import com.ivanlukomskiy.deer.postman.util.JpaConverterJson;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
@Data
@Entity
public class PersonsSet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @Column(length = 100_000)
    @Convert(converter = JpaConverterJson.class)
    private List<Person> persons;

    @Data
    private class Person {
        private Map<String, String> parameters = new HashMap<>();
    }
}
