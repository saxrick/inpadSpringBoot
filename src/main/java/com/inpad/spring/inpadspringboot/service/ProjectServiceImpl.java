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

    @Autowired
    private ProjectDao projectDao;

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    @Override
    @Transactional
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }


    public ProjectDTO saveProject(SignUpProjectDTO projectDTO) {
        System.out.println(projectDTO);

        Project project = projectMapper.signUpToProject(projectDTO);

        Project savedProject = projectRepository.save(project);
        return projectMapper.toProjectDTO(savedProject);
    }

    @Override
    @Transactional
    public Project getProject(int id) {
        return projectDao.getProject(id);
    }

    @Override
    @Transactional
    public void deleteProject(int id) {
        projectDao.deleteProject(id);
    }
}
