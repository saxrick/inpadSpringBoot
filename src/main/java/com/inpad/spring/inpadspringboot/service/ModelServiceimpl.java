package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.ModelDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpModelDTO;
import com.inpad.spring.inpadspringboot.entity.Model;

import com.inpad.spring.inpadspringboot.mapper.ModelMapper;

import com.inpad.spring.inpadspringboot.repositories.ModelRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceimpl implements ModelService{

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ModelDTO saveModel(SignUpModelDTO modelDTO) {
        Model model = modelMapper.signUpToModel(modelDTO);
        Model savedModel = modelRepository.save(model);
        return modelMapper.toModelDTO(savedModel);
    }

    @Override
    public void deleteModel(int id) {
        modelRepository.deleteById((id));
    }

    @Override
    public void saveUpdatedModel(SignUpModelDTO modelDTO, int id) {
        Model updatedModel = modelRepository.getReferenceById(id);
        updatedModel.setModelname(modelDTO.getModelName());
        updatedModel.setModelinfo(modelDTO.getModelInfo());
        updatedModel.setUsers(modelDTO.getUserList());
        modelRepository.save(updatedModel);
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model getModel(int id) {
        return modelRepository.getReferenceById(id);
    }
}
