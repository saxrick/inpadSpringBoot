package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class GeoData {
    private String type;
    private List<Feature> features;
}