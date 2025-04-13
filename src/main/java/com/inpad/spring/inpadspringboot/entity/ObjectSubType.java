package com.inpad.spring.inpadspringboot.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "object_subtype")
public class ObjectSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "object_subtype")
    private String objectSubtype;

    @Column(name = "state")
    private boolean state;

    public ObjectSubType() {
    }

    public ObjectSubType(int id, String objectSubtype, boolean state) {
        this.id = id;
        this.objectSubtype = objectSubtype;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectSubtype() {
        return objectSubtype;
    }

    public void setObjectSubtype(String objectSubtype) {
        this.objectSubtype = objectSubtype;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ObjectSubType{" +
                "id=" + id +
                ", objectSubtype='" + objectSubtype + '\'' +
                ", state=" + state +
                '}';
    }
}
