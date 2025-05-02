package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectDataServiceImpl implements ProjectDataService{

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AreaCalculator areaCalculator;

    @Override
    public GeoDataContainer updateProjectData(int id, GeoDataContainer projectData) {
        System.out.println(id);

        System.out.println(projectData);

        Project updatedProject = projectService.getProject(id);

        GeoDataContainer geoDataContainer = new GeoDataContainer();
        GeoData geoData = projectData.getGeoData();
        List<Feature> features = geoData.getFeatures();
        List<PolygonInfo> polygonsInfo = projectData.getPolygonsInfo();

        features.sort(Feature.COMPARE_BY_ID);
        polygonsInfo.sort(PolygonInfo.COMPARE_BY_ID);

        for (PolygonInfo polygonInfo : polygonsInfo) {
            if (polygonInfo.getArea() == null || polygonInfo.getArea() == 0) {
                Feature feature = features.stream()
                        .filter(feature1 -> polygonInfo.getId().equals(feature1.getId()))
                        .findAny()
                        .orElse(null);
                polygonInfo.setArea(areaCalculator.polygonArea(feature.getGeometry().getCoordinates()));
            }
        }
        geoData.setFeatures(features);
        geoDataContainer.setGeoData(geoData);
        geoDataContainer.setPolygonsInfo(polygonsInfo);
        updatedProject.setProjectdata(geoDataContainer);
        return geoDataContainer;
    }

    @Override
    public GeoDataContainer saveProjectData(int id, GeoDataContainer projectData) {
        return null;
    }
}
