package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;
import com.inpad.spring.inpadspringboot.entity.TechEconPerformanceFactual;
import com.inpad.spring.inpadspringboot.repositories.CoefficientFactualRepository;
import com.inpad.spring.inpadspringboot.repositories.TechEconPerformanceFactualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CoefficientFactualServiceImpl implements CoefficientFactualService {

    @Autowired
    private final CoefficientFactualRepository coefficientFactualRepository;

    @Autowired
    private final TechEconPerformanceFactualRepository techEconPerformanceFactualRepository;

    @Override
    public CoefficientFactual saveCoefficientFactual(CoefficientFactual coefficientFactual) {
        TechEconPerformanceFactual createdTechEconPerformanceFactual = new TechEconPerformanceFactual();
        createdTechEconPerformanceFactual.setApartsArea(123.45f);
        techEconPerformanceFactualRepository.save(createdTechEconPerformanceFactual);
        return coefficientFactualRepository.save(coefficientFactual);
    }

    @Override
    public void deleteCoefficientFactual(int id) {
        coefficientFactualRepository.deleteById(id);
    }

    @Override
    public void saveUpdatedCoefficientFactual(CoefficientFactual coefficientFactual, int id) {
        CoefficientFactual updatedCoefficientFactual = coefficientFactualRepository.getReferenceById(id);
        updatedCoefficientFactual.setProjectId(coefficientFactual.getProjectId());
        updatedCoefficientFactual.setTepId(coefficientFactual.getTepId());
        updatedCoefficientFactual.setFlatAreaCoeff(coefficientFactual.getFlatAreaCoeff());
        updatedCoefficientFactual.setCommAreaCoeff(coefficientFactual.getCommAreaCoeff());
        updatedCoefficientFactual.setParkingFlatCoeff(coefficientFactual.getParkingFlatCoeff());
        updatedCoefficientFactual.setParkingCommCoeff(coefficientFactual.getParkingCommCoeff());
        updatedCoefficientFactual.setResidentsCoeff(coefficientFactual.getResidentsCoeff());
        updatedCoefficientFactual.setDdu10Coeff(coefficientFactual.getDdu10Coeff());
        updatedCoefficientFactual.setUtilCoeff(coefficientFactual.getUtilCoeff());
        coefficientFactualRepository.save(updatedCoefficientFactual);
    }

    @Override
    public CoefficientFactual getCoefficientFactual(int id) {
        return coefficientFactualRepository.getReferenceById(id);
    }
}
