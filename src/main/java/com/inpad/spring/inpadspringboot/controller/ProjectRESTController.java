package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.entity.User;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import com.inpad.spring.inpadspringboot.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")

public class ProjectRESTController {


    private final ProjectRepository repository;

    private final ProjectService projectService;

    @GetMapping("/all")
    public List<ProjectDTO> getAllProjects(){
        ProjectDTO projectDTO = new ProjectDTO();
        return projectDTO.getProjectDTOList(repository.findAll());
    }

    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable int id){
        ProjectDTO projectDTO = new ProjectDTO();
        List<ProjectDTO> projectDTOList = projectDTO.getProjectDTOList(repository.findAll());
        return projectDTOList.get(id - 1);
    }


    @PostMapping("/")
    public ResponseEntity<ProjectDTO> addNewProject(@RequestBody SignUpProjectDTO projectDTO){

        ProjectDTO createdProject = projectService.saveProject(projectDTO);
        return ResponseEntity.created(URI.create("/projects/" + createdProject.getId()))
                .body(createdProject);
    }

//    @PutMapping("/")
//    public Project updateProject(@RequestBody SignUpProjectDTO projectDTO){
//
//        ProjectDTO updatedProject = projectService.saveProject(projectDTO);
//
//    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable int id){
        Project project = projectService.getProject(id);
        projectService.deleteProject(id);
        return "Project with id = " + id + " was deleted";
    }
}
