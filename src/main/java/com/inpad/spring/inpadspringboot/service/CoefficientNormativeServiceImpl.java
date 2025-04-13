package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientNormative;
import com.inpad.spring.inpadspringboot.repositories.CoefficientNormativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CoefficientNormativeServiceImpl implements CoefficientNormativeService{

    @Autowired
    private final CoefficientNormativeRepository coefficientNormativeRepository;

    @Override
    public CoefficientNormative saveCoefficientNormative(CoefficientNormative coefficientNormative) {
        return coefficientNormativeRepository.save(coefficientNormative);
    }

    @Override
    public void deleteCoefficientNormative(int id) {
        coefficientNormativeRepository.deleteById(id);
    }

    @Override
    public void saveUpdatedCoefficientNormative(CoefficientNormative coefficientNormative, int id) {
        CoefficientNormative updatedCoefficientNormative = coefficientNormativeRepository.getReferenceById(id);
        updatedCoefficientNormative.setProjectId(coefficientNormative.getProjectId());
        updatedCoefficientNormative.setModelId(coefficientNormative.getModelId());
        updatedCoefficientNormative.setTepId(coefficientNormative.getTepId());
        updatedCoefficientNormative.setFlatAreaCoeff(coefficientNormative.getFlatAreaCoeff());
        updatedCoefficientNormative.setDdu10Coeff(coefficientNormative.getDdu10Coeff());
        updatedCoefficientNormative.setResidentsCoeff(coefficientNormative.getResidentsCoeff());
        updatedCoefficientNormative.setChildCoeff(coefficientNormative.getChildCoeff());
        updatedCoefficientNormative.setSchoolCoeff(coefficientNormative.getSchoolCoeff());
        updatedCoefficientNormative.setDdu25Coeff(coefficientNormative.getDdu25Coeff());
        updatedCoefficientNormative.setPlaygroundCoeff(coefficientNormative.getPlaygroundCoeff());
        updatedCoefficientNormative.setSportgroundCoeff(coefficientNormative.getSportgroundCoeff());
        updatedCoefficientNormative.setRecreationCoeff(coefficientNormative.getRecreationCoeff());
        updatedCoefficientNormative.setUtilCoeff(coefficientNormative.getUtilCoeff());
        coefficientNormativeRepository.save(updatedCoefficientNormative);
    }

    @Override
    public CoefficientNormative getCoefficientNormative(int id) {
        return coefficientNormativeRepository.getReferenceById(id);
    }
}
