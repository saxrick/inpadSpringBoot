package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> getAllProjects();
    public ProjectDTO saveProject(SignUpProjectDTO projectDTO);
    public Project getProject(int id);
    public void deleteProject(int id);
}
