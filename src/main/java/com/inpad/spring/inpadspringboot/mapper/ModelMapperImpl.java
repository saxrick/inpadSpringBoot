package com.inpad.spring.inpadspringboot.mapper;

import com.inpad.spring.inpadspringboot.dto.ModelDTO;
import com.inpad.spring.inpadspringboot.dto.ProjectDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpModelDTO;
import com.inpad.spring.inpadspringboot.entity.Model;
import com.inpad.spring.inpadspringboot.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements ModelMapper{
    @Override
    public ModelDTO toModelDTO(Model model) {
        if(model == null){
            return null;
        }
        ModelDTO modelDTO = new ModelDTO();
        return modelDTO.getModelDTO(model);
    }

    @Override
    public Model signUpToModel(SignUpModelDTO modelDTO) {
        if ( modelDTO == null ) {
            return null;
        }
        Model.ModelBuilder model = Model.builder();
        model.modelname( modelDTO.getModelName() );
        model.modelinfo( modelDTO.getModelInfo() );
        model.state( modelDTO.isState() );
        model.users( modelDTO.getUserList() );

        return model.build();
    }
}
