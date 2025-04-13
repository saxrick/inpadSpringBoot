package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;

public interface CoefficientFactualService {
    public CoefficientFactual saveCoefficientFactual(CoefficientFactual coefficientFactual);

    public void deleteCoefficientFactual(int id);

    public void saveUpdatedCoefficientFactual(CoefficientFactual coefficientFactual, int id);

    public CoefficientFactual getCoefficientFactual(int id);
}
