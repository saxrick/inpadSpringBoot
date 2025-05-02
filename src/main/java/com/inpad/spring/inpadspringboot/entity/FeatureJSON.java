package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;

@Entity
public class FeatureJSON {

    @Id
    private String id;

    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    private GeometryJSON geometry;


}
