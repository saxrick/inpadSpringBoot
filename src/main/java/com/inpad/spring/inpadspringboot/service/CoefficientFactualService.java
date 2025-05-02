package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;

public interface CoefficientFactualService {
    public CoefficientFactual saveCoefficientFactual(CoefficientFactual coefficientFactual);

    public void deleteCoefficientFactual(int id);

    public void saveUpdatedCoefficientFactual(CoefficientFactual coefficientFactual, String id);

    public CoefficientFactual getCoefficientFactual(String modelId);
}
