package com.inpad.spring.inpadspringboot.dto;

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
public class ProjectDTO {
    private int id;
    private String projectname;
    private boolean state;

    private String projectinfo;

    private List<UserDTO> userList;


    @Transactional
    public ProjectDTO getProjectDTO(Project project){
        userList = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectname(project.getProjectname());
        projectDTO.setState(project.isState());
        projectDTO.setProjectinfo(project.getProjectinfo());
        for (User user : project.getUsers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setState(user.isState());
            userDTO.setLogin(user.getLogin());
            userDTO.setRole(user.getRole());
            userList.add(userDTO);
        }
        projectDTO.setUserList(userList);
        return projectDTO;
    }

    @Transactional
    public List<ProjectDTO> getProjectDTOList(List<Project> projectList){
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (Project project : projectList){
            ProjectDTO projectDTO = getProjectDTO(project);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public String getProjectinfo() {
        return projectinfo;
    }

    public void setProjectinfo(String projectinfo) {
        this.projectinfo = projectinfo;
    }
}
