package com.inpad.spring.inpadspringboot.dto;

import com.inpad.spring.inpadspringboot.entity.Model;
import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Builder
@Data
@EnableTransactionManagement
public class ModelDTO {
    private int id;
    private String modelname;
    private boolean state;

    private String modelinfo;

    private List<UserDTO> userList;

    @Transactional
    public ModelDTO getModelDTO(Model model){
        userList = new ArrayList<>();
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(model.getId());
        modelDTO.setModelname(model.getModelname());
        modelDTO.setState(model.isState());
        modelDTO.setModelinfo(model.getModelinfo());
        for (User user : model.getUsers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setState(user.isState());
            userDTO.setLogin(user.getLogin());
            userDTO.setRole(user.getRole());
            userList.add(userDTO);
        }
        modelDTO.setUserList(userList);
        return modelDTO;
    }

    @Transactional
    public List<ModelDTO> getModelDTOList(List<Model> modelList){
        List<ModelDTO> modelDTOList = new ArrayList<>();
        for (Model model : modelList){
            ModelDTO modelDTO = getModelDTO(model);
            modelDTOList.add(modelDTO);
        }
        return modelDTOList;
    }
}
