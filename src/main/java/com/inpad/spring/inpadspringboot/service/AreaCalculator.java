package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.Feature;

import java.util.List;

public interface AreaCalculator {

    public double polygonArea(double[][][] coordinates);

}
