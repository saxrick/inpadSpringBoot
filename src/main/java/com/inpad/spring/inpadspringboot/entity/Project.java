package com.inpad.spring.inpadspringboot.entity;


import com.inpad.spring.inpadspringboot.converter.GeoDataConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.Serializable;
import java.util.*;

@Builder
@Data
@Entity
@Table(name = "projects")

public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "projectname")
    private String projectname;
    @Column(name = "state")
    private boolean state;

    @Column(name = "projectinfo")
    private String projectinfo;

    @Lob
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "projectdata", columnDefinition = "json", nullable = true)
    private GeoDataContainer projectdata;

    @Column(name = "dt_creation")
    private Date dtCreation;

    @Column(name = "dt_update")
    private Date dtUpdate;

    @Column(name = "start_coordinates")
    private String startCoordinates;

    @Column(name = "inside_coordinates")
    private String insideCoordinates;

    @Column(name = "outside_coordinates")
    private String outsideCoordinates;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_project"
            , joinColumns = @JoinColumn(name = "project_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<CoefficientFactual> coefficientFactualList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<CoefficientNormative> coefficientNormativeList;

    public enum UserToProjectType {LEAVE, CHAT, JOIN}

    public void addUserToProject(User user) {
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }

    public Project() {
    }

    public Project(int id, String projectname, boolean state, String projectinfo, GeoDataContainer projectdata, Date dtCreation, Date dtUpdate, String startCoordinates, String insideCoordinates, String outsideCoordinates, List<User> users, List<CoefficientFactual> coefficientFactualList, List<CoefficientNormative> coefficientNormativeList) {
        this.id = id;
        this.projectname = projectname;
        this.state = state;
        this.projectinfo = projectinfo;
        this.projectdata = projectdata;
        this.dtCreation = dtCreation;
        this.dtUpdate = dtUpdate;
        this.startCoordinates = startCoordinates;
        this.insideCoordinates = insideCoordinates;
        this.outsideCoordinates = outsideCoordinates;
        this.users = users;
        this.coefficientFactualList = coefficientFactualList;
        this.coefficientNormativeList = coefficientNormativeList;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return id == project.id && state == project.state && Objects.equals(projectname, project.projectname) && Objects.equals(projectinfo, project.projectinfo) && Objects.equals(projectdata, project.projectdata) && Objects.equals(users, project.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectname, state, projectinfo, projectdata, users);
    }
}

