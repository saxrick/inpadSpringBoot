package com.inpad.spring.inpadspringboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Comparator;
import java.util.Objects;

@Data
public class PolygonInfo {
    private String id;
    @Nullable
    private Double area;
    private int floors;
    @JsonProperty("commFloors")
    private int commercialFloors;
    @JsonProperty("floorHeight")
    private double floorHeight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolygonInfo that)) return false;
        return Double.compare(getArea(), that.getArea()) == 0 && getFloors() == that.getFloors() && getCommercialFloors() == that.getCommercialFloors() && Double.compare(getFloorHeight(), that.getFloorHeight()) == 0 && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static final Comparator<PolygonInfo> COMPARE_BY_ID = new Comparator<PolygonInfo>() {
        @Override
        public int compare(PolygonInfo lhs, PolygonInfo rhs) {
            return lhs.hashCode() - rhs.hashCode();
        }
    };
}
