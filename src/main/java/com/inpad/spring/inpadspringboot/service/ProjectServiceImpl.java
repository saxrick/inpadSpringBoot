package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.converter.GeoDataConverter;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Project;

import com.inpad.spring.inpadspringboot.mapper.ProjectMapper;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private SimpMessagingTemplate messagingTemplate;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    private final GeoDataConverter geoDataConverter;
    

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project getProject(int id) {
        return projectRepository.getReferenceById(id);
    }


    public ProjectDTO saveProject(SignUpProjectDTO projectDTO) {
        Project project = projectMapper.signUpToProject(projectDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toProjectDTO(savedProject);
    }

    public void saveUpdatedProject(SignUpProjectDTO projectDTO, int id) {
        Project updatedProject = projectRepository.getReferenceById(id);
        updatedProject.setProjectname(projectDTO.getProjectName());
        updatedProject.setProjectinfo(projectDTO.getProjectInfo());
        updatedProject.setProjectdata(projectDTO.getProjectData());
        updatedProject.setUsers(projectDTO.getUserList());
        updatedProject.setDtUpdate(projectDTO.getDtUpdate());
        updatedProject.setDtCreation(projectDTO.getDtCreation());
        updatedProject.setStartCoordinates(projectDTO.getStartCoordinates());
        updatedProject.setInsideCoordinates(projectDTO.getInsideCoordinates());
        updatedProject.setOutsideCoordinates(projectDTO.getOutsideCoordinates());
        projectRepository.save(updatedProject);

    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}
