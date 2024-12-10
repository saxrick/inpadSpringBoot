package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "modelname")
    private String modelname;
    @Column(name = "state")
    private boolean state;

    @Column(name = "modelinfo")
    private String modelinfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_model"
            , joinColumns = @JoinColumn(name = "model_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public void addUserToModel(User user) {
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelname='" + modelname + '\'' +
                ", state=" + state +
                ", modelinfo='" + modelinfo + '\'' +
                ", users=" + users +
                '}';
    }

    public Model() {
    }

    public Model(int id, String modelname, boolean state, String modelinfo, List<User> users) {
        this.id = id;
        this.modelname = modelname;
        this.state = state;
        this.modelinfo = modelinfo;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getModelinfo() {
        return modelinfo;
    }

    public void setModelinfo(String modelinfo) {
        this.modelinfo = modelinfo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
