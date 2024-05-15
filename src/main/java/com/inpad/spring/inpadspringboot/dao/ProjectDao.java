package com.inpad.spring.inpadspringboot.dao;

import com.inpad.spring.inpadspringboot.entity.Project;


import java.util.List;

public interface ProjectDao {
    public List<Project> getAllProjects();
    public void saveProject(Project project);
    public Project getProject(int id);
    public void deleteProject(int id);
}
