package com.inpad.spring.inpadspringboot.mapper;

import com.inpad.spring.inpadspringboot.dto.ModelDTO;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpModelDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Model;
import com.inpad.spring.inpadspringboot.entity.Project;

public interface ModelMapper {
    ModelDTO toModelDTO(Model model);

    Model signUpToModel(SignUpModelDTO modelDTO);
}
