package com.inpad.spring.inpadspringboot.dao;

import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Project> getAllProjects() {
        Query query = entityManager.createQuery("from Project ");
        List<Project> allProjects = query.getResultList();
        return allProjects;
    }

    @Override
    public void saveProject(Project project) {
        Project newProject = entityManager.merge(project);
        project.setId(newProject.getId());
    }

    @Override
    public Project getProject(int id) {
        Project project = entityManager.find(Project.class, id);
        return project;
    }

    @Override
    public void deleteProject(int id) {
        Query query = entityManager.createQuery("delete Project where id =:projectId");
        query.setParameter("projectId", id);
        query.executeUpdate();
    }
}
