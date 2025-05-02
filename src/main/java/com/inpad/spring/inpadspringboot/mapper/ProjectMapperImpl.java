package com.inpad.spring.inpadspringboot.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inpad.spring.inpadspringboot.converter.GeoDataConverter;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.GeoDataContainer;
import com.inpad.spring.inpadspringboot.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper{
    @Override
    public ProjectDTO toProjectDTO(Project project) {
        if(project == null){
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        return projectDTO.getProjectDTO(project);
    }

    @Override
    public Project signUpToProject(SignUpProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }
        Project.ProjectBuilder project = Project.builder();
        project.projectname( projectDTO.getProjectName() );
        project.projectinfo( projectDTO.getProjectInfo() );
        try {
            project.projectdata( GeoDataConverter.fromJson(String.valueOf(projectDTO.getProjectData())) );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        project.state( projectDTO.isState() );
        project.users( projectDTO.getUserList() );

        return project.build();
    }
}
