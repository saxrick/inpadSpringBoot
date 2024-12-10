package com.inpad.spring.inpadspringboot.dto;


import com.inpad.spring.inpadspringboot.entity.Model;
import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.entity.User;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Builder
@Data
@EnableTransactionManagement
public class UserDTO {

    private int id;
    private String username;
    private boolean state;
    private String login;
    private String role;
    private String token;
    private List<ProjectDTO> projectList;
    private List<ModelDTO> modelList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }


    @Transactional
    public UserDTO getUserDTO(User user){
        projectList = new ArrayList<>();
        modelList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setState(user.isState());
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole());
        for (Project project : user.getProjects()){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setProjectname(project.getProjectname());
            projectDTO.setState(project.isState());
            projectDTO.setProjectinfo(project.getProjectinfo());
            projectList.add(projectDTO);
        }
        userDTO.setProjectList(projectList);

        for (Model model : user.getModels()){
            ModelDTO modelDTO = new ModelDTO();
            modelDTO.setId(model.getId());
            modelDTO.setModelname(model.getModelname());
            modelDTO.setState(model.isState());
            modelDTO.setModelinfo(model.getModelinfo());
            modelList.add(modelDTO);
        }
        userDTO.setModelList(modelList);

        return userDTO;
    }
    @Transactional
    public List<UserDTO> getUserDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList){
            UserDTO userDTO = getUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
