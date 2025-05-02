package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "object_type")
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "object_type")
    private String objectType;

    @Column(name = "expect_subtype")
    private boolean expectSubType;

    @Column(name = "state")
    private boolean state;

    public ObjectType() {
    }

    public ObjectType(int id, String objectType, boolean expectSubType, boolean state) {
        this.id = id;
        this.objectType = objectType;
        this.expectSubType = expectSubType;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public boolean isExpectSubType() {
        return expectSubType;
    }

    public void setExpectSubType(boolean expectSubType) {
        this.expectSubType = expectSubType;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ObjectType{" +
                "id=" + id +
                ", objectType='" + objectType + '\'' +
                ", expectSubType=" + expectSubType +
                ", state=" + state +
                '}';
    }


}

