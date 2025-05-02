package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Entity
public class GeometryJSON {

    @Id
    private String id;
    @Transient
    private List<List<List<Double>>> coordinates;

    private String type;

    public GeometryJSON() {
    }

    public GeometryJSON(List<List<List<Double>>> coordinates, String type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GeometryJSON{" +
                "coordinates=" + coordinates +
                ", type='" + type + '\'' +
                '}';
    }
}
