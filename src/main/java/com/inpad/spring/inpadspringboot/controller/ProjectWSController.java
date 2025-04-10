package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import com.inpad.spring.inpadspringboot.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@Transactional
public class ProjectWSController {

    @Autowired
    ProjectService projectService;

    private final ProjectRepository projectRepository;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public JSONObject sendMessage(int id) {

        Project gotProject = projectService.getProject(id);

        return gotProject.getProjectdata();
    }
}
