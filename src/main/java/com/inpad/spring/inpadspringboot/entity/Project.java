package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "projects")
public class Project {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_project"
            , joinColumns = @JoinColumn(name = "project_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public void addUserToProject(User user) {
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectname='" + projectname + '\'' +
                ", state=" + state +
                ", projectinfo='" + projectinfo + '\'' +
                ", users=" + users +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public Project(String projectname, boolean state, String projectinfo, List<User> users) {
        this.projectname = projectname;
        this.state = state;
        this.projectinfo = projectinfo;
        this.users = users;
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

}

