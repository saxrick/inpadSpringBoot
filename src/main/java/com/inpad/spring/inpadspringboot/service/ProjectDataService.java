package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.GeoDataContainer;

public interface ProjectDataService {
    public GeoDataContainer updateProjectData(int id, GeoDataContainer projectData);

    public GeoDataContainer saveProjectData(int id, GeoDataContainer projectData);
}
