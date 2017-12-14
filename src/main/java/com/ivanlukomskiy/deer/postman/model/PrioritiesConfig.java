package com.ivanlukomskiy.deer.postman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivanlukomskiy.deer.postman.util.JpaConverterJson;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
@Data
@Entity
public class PrioritiesConfig {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(length = 100_000)
    @Convert(converter = JpaConverterJson.class)
    private Set<Priority> priorities;

    @Data
    private class Priority {
        @JsonProperty("property_id")
        private String propertyId;
        @JsonProperty("priority_type")
        private PriorityType priorityType;
    }

    public enum PriorityType {
        PREFER_IF_DIFFERENT
    }
}