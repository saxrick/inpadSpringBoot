package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GeoDataJSON {


    @Id
    private String id;

    private float area;

    private int floors;

    private int commFloors;

    private float floorHeight;


}
