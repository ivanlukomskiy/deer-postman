package com.ivanlukomskiy.deer.postman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
public class JpaConverterJson implements AttributeConverter<Object, String> {
    private static final Logger logger = LoggerFactory.getLogger(JpaConverterJson.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize entity: {}", meta, e);
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException e) {
            logger.error("Failed to deserialize entity: {}", dbData, e);
            return null;
        }
    }
}