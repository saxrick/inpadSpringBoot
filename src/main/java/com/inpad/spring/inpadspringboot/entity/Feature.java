package com.inpad.spring.inpadspringboot.entity;

import lombok.Data;

import java.util.Comparator;
import java.util.Objects;

@Data
public class Feature {
    private String id;
    private String type;
    private Object properties;
    private Geometry geometry;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature feature)) return false;
        return Objects.equals(getId(), feature.getId()) && Objects.equals(getType(), feature.getType()) && Objects.equals(getProperties(), feature.getProperties()) && Objects.equals(getGeometry(), feature.getGeometry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static final Comparator<Feature> COMPARE_BY_ID = new Comparator<Feature>() {
        @Override
        public int compare(Feature lhs, Feature rhs) {
            return lhs.hashCode() - rhs.hashCode();
        }
    };
}