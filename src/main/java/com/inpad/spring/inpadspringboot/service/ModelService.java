package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.ModelDTO;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpModelDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpProjectDTO;
import com.inpad.spring.inpadspringboot.entity.Model;
import com.inpad.spring.inpadspringboot.entity.Project;

import java.util.List;

public interface ModelService {
    public ModelDTO saveModel(SignUpModelDTO modelDTO);
    public void deleteModel(int id);
    public void saveUpdatedModel(SignUpModelDTO modelDTO, int id);
    public List<Model> getAllModels();
    public Model getModel(int id);
}
