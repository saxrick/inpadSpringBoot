package com.inpad.spring.inpadspringboot.entity;


import com.inpad.spring.inpadspringboot.converter.JsonToMapConverter;
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
    @Convert(attributeName = "data", converter = JsonToMapConverter.class)
    private JSONObject projectdata;

    @Column(name = "dt_creation")
    private Date dtCreation;

    @Column(name = "dt_update")
    private Date dtUpdate;

    @Column(name = "start_coordinates")
    private String startCoordinates;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_project"
            , joinColumns = @JoinColumn(name = "project_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public enum UserToProjectType {LEAVE, CHAT, JOIN}

    public void addUserToProject(User user) {
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }

    public Project(int id, String projectname, boolean state, String projectinfo, JSONObject projectdata, Date dtCreation, Date dtUpdate, String startCoordinates, List<User> users) {
        this.id = id;
        this.projectname = projectname;
        this.state = state;
        this.projectinfo = projectinfo;
        this.projectdata = projectdata;
        this.dtCreation = dtCreation;
        this.dtUpdate = dtUpdate;
        this.startCoordinates = startCoordinates;
        this.users = users;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectname='" + projectname + '\'' +
                ", state=" + state +
                ", projectinfo='" + projectinfo + '\'' +
                ", projectdata=" + projectdata +
                ", dtCreation=" + dtCreation +
                ", dtUpdate=" + dtUpdate +
                ", startCoordinates='" + startCoordinates + '\'' +
                ", users=" + users +
                '}';
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

    public JSONObject getProjectdata() {
        return projectdata;
    }

    public void setProjectdata(JSONObject projectdata) {
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

