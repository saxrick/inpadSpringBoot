package com.inpad.spring.inpadspringboot.dto;

import com.inpad.spring.inpadspringboot.entity.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private GeoDataContainer projectdata;
    private List<UserDTO> userList;
    private Date dtCreation;
    private Date dtUpdate;
    private String startCoordinates;
    private String insideCoordinates;
    private String outsideCoordinates;
    private List<CoefficientFactual> coefficientFactualList;
    private List<CoefficientNormative> coefficientNormativeList;


    @Transactional
    public ProjectDTO getProjectDTO(Project project){
        userList = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectname(project.getProjectname());
        projectDTO.setState(project.isState());
        projectDTO.setProjectinfo(project.getProjectinfo());
        projectDTO.setProjectdata(project.getProjectdata());
        projectDTO.setDtCreation(project.getDtCreation());
        projectDTO.setDtUpdate(project.getDtUpdate());
        projectDTO.setStartCoordinates(project.getStartCoordinates());
        projectDTO.setInsideCoordinates(project.getInsideCoordinates());
        projectDTO.setOutsideCoordinates(project.getOutsideCoordinates());
        projectDTO.setCoefficientFactualList(project.getCoefficientFactualList());
        projectDTO.setCoefficientNormativeList(project.getCoefficientNormativeList());
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

    public GeoDataContainer getProjectdata() {
        return projectdata;
    }

    public void setProjectdata(GeoDataContainer projectdata) {
        this.projectdata = projectdata;
    }

    public Date getDtCreation() {
        return dtCreation;
    }

    public Date getDtUpdate() {
        return dtUpdate;
    }

    public String getStartCoordinates() {
        return startCoordinates;
    }

    public void setStartCoordinates(String startCoordinates) {
        this.startCoordinates = startCoordinates;
    }

    public void setDtCreation(Date dtCreation) {
        this.dtCreation = dtCreation;
    }

    public void setDtUpdate(Date dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getInsideCoordinates() {
        return insideCoordinates;
    }

    public void setInsideCoordinates(String insideCoordinates) {
        this.insideCoordinates = insideCoordinates;
    }

    public String getOutsideCoordinates() {
        return outsideCoordinates;
    }

    public void setOutsideCoordinates(String outsideCoordinates) {
        this.outsideCoordinates = outsideCoordinates;
    }

    public List<CoefficientFactual> getCoefficientFactualList() {
        return coefficientFactualList;
    }

    public void setCoefficientFactualList(List<CoefficientFactual> coefficientFactualList) {
        this.coefficientFactualList = coefficientFactualList;
    }

    public List<CoefficientNormative> getCoefficientNormativeList() {
        return coefficientNormativeList;
    }

    public void setCoefficientNormativeList(List<CoefficientNormative> coefficientNormativeList) {
        this.coefficientNormativeList = coefficientNormativeList;
    }
}
