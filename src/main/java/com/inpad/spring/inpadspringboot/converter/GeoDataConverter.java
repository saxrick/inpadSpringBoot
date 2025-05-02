package com.inpad.spring.inpadspringboot.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inpad.spring.inpadspringboot.entity.GeoDataContainer;
import org.springframework.stereotype.Component;

@Component
public class GeoDataConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static GeoDataContainer fromJson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, GeoDataContainer.class);
    }

    public String toJson(GeoDataContainer geoDataContainer) throws JsonProcessingException {
        return objectMapper.writeValueAsString(geoDataContainer);
    }
}