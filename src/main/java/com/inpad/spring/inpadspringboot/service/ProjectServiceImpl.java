package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dao.ProjectDao;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.mapper.ProjectMapper;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectDTO saveProject(SignUpProjectDTO projectDTO) {

        Project project = projectMapper.signUpToProject(projectDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toProjectDTO(savedProject);
    }

    public void saveUpdatedProject(SignUpProjectDTO projectDTO, int id) {
        Project updatedProject = projectRepository.getReferenceById(id);
        updatedProject.setProjectname(projectDTO.getProjectName());
        updatedProject.setProjectinfo(projectDTO.getProjectInfo());
        updatedProject.setUsers(projectDTO.getUserList());
        projectRepository.save(updatedProject);
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}
