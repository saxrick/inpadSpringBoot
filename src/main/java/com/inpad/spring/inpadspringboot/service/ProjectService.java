package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Project;

import java.util.List;

public interface ProjectService {
    public ProjectDTO saveProject(SignUpProjectDTO projectDTO);
    public void deleteProject(int id);
    public void saveUpdatedProject(SignUpProjectDTO projectDTO, int id);
    public List<Project> getAllProjects();
    public Project getProject(int id);
}
