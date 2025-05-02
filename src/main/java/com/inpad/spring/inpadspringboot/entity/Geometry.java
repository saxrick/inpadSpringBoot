package com.inpad.spring.inpadspringboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Geometry {
    private String type;
    private double[][][] coordinates;
}
