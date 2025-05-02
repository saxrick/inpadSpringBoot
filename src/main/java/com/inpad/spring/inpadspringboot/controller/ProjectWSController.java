package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.*;
import com.inpad.spring.inpadspringboot.service.ProjectDataService;
import com.inpad.spring.inpadspringboot.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@Transactional

public class ProjectWSController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectDataService projectDataService;

    @MessageMapping("/sendMessage/{id}")
    @SendTo("/topic/messages")
    public GeoDataContainer sendMessage(GeoDataContainer geoDataContainer) {
        System.out.println(geoDataContainer);
        GeoDataContainer projectdata = projectService.getProject(geoDataContainer.getProjectId()).getProjectdata();
// Добавить сравнение хэшкода

        if (!geoDataContainer.equals(projectdata)) {
            return projectDataService.updateProjectData(geoDataContainer.getProjectId(), geoDataContainer);
        }

        else {
            return projectdata;
        }
    }
}