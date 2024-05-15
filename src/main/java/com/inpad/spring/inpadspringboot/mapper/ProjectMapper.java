package com.inpad.spring.inpadspringboot.mapper;

import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Project;

public interface ProjectMapper {
    ProjectDTO toProjectDTO(Project project);

    Project signUpToProject(SignUpProjectDTO projectDTO);
}
