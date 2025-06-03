package com.inpad.spring.inpadspringboot.dto;


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
    private String login;
    private List<ProjectDTO> projectList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        for (Project project : user.getProjects()){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setProjectname(project.getProjectname());
            projectDTO.setState(project.isState());
            projectDTO.setProjectinfo(project.getProjectinfo());
            projectDTO.setProjectdata(project.getProjectdata());
            projectDTO.setDtUpdate(project.getDtUpdate());
            projectDTO.setDtCreation(project.getDtCreation());
            projectDTO.setStartCoordinates(project.getStartCoordinates());
            projectDTO.setInsideCoordinates(project.getInsideCoordinates());
            projectDTO.setOutsideCoordinates(project.getOutsideCoordinates());
            projectDTO.setCoefficientNormativeList(project.getCoefficientNormativeList());
            projectDTO.setCoefficientFactualList(project.getCoefficientFactualList());
            projectList.add(projectDTO);
        }
        userDTO.setProjectList(projectList);

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
