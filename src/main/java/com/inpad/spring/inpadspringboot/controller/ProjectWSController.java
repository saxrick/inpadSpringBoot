package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.*;
import com.inpad.spring.inpadspringboot.service.ProjectDataService;
import com.inpad.spring.inpadspringboot.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@Transactional

public class ProjectWSController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectDataService projectDataService;

    @Autowired
    SimpUserRegistry userRegistry;

    @MessageMapping("/sendMessage/{id}")
    @SendTo("/topic/messages/{id}")
    public GeoDataContainer sendMessage(GeoDataContainer geoDataContainer) {
        List<String> subs = getSubscribers("/topic/messages/");
        GeoDataContainer projectdata = projectService.getProject(geoDataContainer.getProjectId()).getProjectdata();
        if (!geoDataContainer.equals(projectdata)) {
            return projectDataService.updateProjectData(geoDataContainer.getProjectId(), geoDataContainer);
        }
        else {
            return projectdata;
        }
    }

    private List<String> getSubscribers(String topic) {
        return userRegistry.getUsers().stream()
                .filter(user -> user.getSessions().stream()
                        .anyMatch(session -> session.getSubscriptions().stream()
                                .anyMatch(subscription -> subscription.getDestination().equals(topic))))
                .map(SimpUser::getName)
                .collect(Collectors.toList());
    }
}