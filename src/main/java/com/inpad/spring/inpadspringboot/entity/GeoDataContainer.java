package com.inpad.spring.inpadspringboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class GeoDataContainer {

    @JsonProperty("projectId")
    private int projectId;

    @JsonProperty("geoData")
    private GeoData geoData;

    @JsonProperty("polygonInfo")
    private List<PolygonInfo> polygonsInfo;

    @Override
    public boolean equals(Object o) {
        System.out.println('1');

        if (this == o) return true;
        if (!(o instanceof GeoDataContainer that)) return false;
        return Objects.equals(getGeoData(), that.getGeoData()) && Objects.equals(getPolygonsInfo(), that.getPolygonsInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeoData(), getPolygonsInfo());
    }
}