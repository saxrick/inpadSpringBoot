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
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectRESTController {

    Logger log = Logger.getLogger(ProjectRESTController.class.getName());


    @Autowired
    ProjectService projectService;

    @GetMapping("/all")
    public List<ProjectDTO> getAllProjects(){
        log.info(java.time.LocalDateTime.now() + " Запрошен список проектов");
        ProjectDTO projectDTO = new ProjectDTO();
        return projectDTO.getProjectDTOList(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable int id){
        log.info(java.time.LocalDateTime.now() + " Запрошен проект с id " + id);
        ProjectDTO projectDTO = new ProjectDTO();
        ProjectDTO gotProjectDTO = projectDTO.getProjectDTO(projectService.getProject(id));
        return gotProjectDTO;
    }


    @PostMapping("/")
    public ResponseEntity<ProjectDTO> addNewProject(@RequestBody SignUpProjectDTO projectDTO){
        ProjectDTO createdProject = projectService.saveProject(projectDTO);
        log.info(java.time.LocalDateTime.now() + " Создан проект с id " + createdProject.getId());
        return ResponseEntity.created(URI.create("/projects/" + createdProject.getId()))
                .body(createdProject);
    }

    @PutMapping("/{id}")
    public void updateProject(@RequestBody SignUpProjectDTO projectDTO, @PathVariable int id){
        projectService.saveUpdatedProject(projectDTO, id);

        log.info(java.time.LocalDateTime.now() + " Обновлен проект с id " + id);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable int id){
        log.info(java.time.LocalDateTime.now() + " Удален проект с id " + id);
        projectService.deleteProject(id);
        return "Project with id = " + id + " was deleted";
    }
}
