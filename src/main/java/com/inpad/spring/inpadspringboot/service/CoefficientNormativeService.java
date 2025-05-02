package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;
import com.inpad.spring.inpadspringboot.entity.CoefficientNormative;

public interface CoefficientNormativeService {
    public CoefficientNormative saveCoefficientNormative(CoefficientNormative coefficientNormative);

    public void deleteCoefficientNormative(int id);

    public void saveUpdatedCoefficientNormative(CoefficientNormative coefficientNormative, String id);

    public CoefficientNormative getCoefficientNormative(String id);
}

