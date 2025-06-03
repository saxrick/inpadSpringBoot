package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.*;
import com.inpad.spring.inpadspringboot.repositories.CoefficientFactualRepository;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import com.inpad.spring.inpadspringboot.repositories.TechEconPerformanceFactualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CoefficientFactualServiceImpl implements CoefficientFactualService {

    @Autowired
    private final CoefficientFactualRepository coefficientFactualRepository;

    @Autowired
    private final TechEconPerformanceFactualRepository techEconPerformanceFactualRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private AreaCalculator areaCalculator;


    @Override
    public CoefficientFactual saveCoefficientFactual(CoefficientFactual coefficientFactual) {
        GeoDataContainer geoDataContainer = projectRepository.getReferenceById(coefficientFactual.getProjectId()).getProjectdata();
        PolygonInfo polygonInfo = geoDataContainer.getPolygonsInfo().stream()
                .filter(polygonInfo1 -> coefficientFactual.getModelId().equals(polygonInfo1.getId()))
                .findAny()
                .orElse(null);

        Feature feature = geoDataContainer.getGeoData().getFeatures().stream()
                .filter(feature1 -> coefficientFactual.getModelId().equals(feature1.getId()))
                .findAny()
                .orElse(null);


        TechEconPerformanceFactual createdTechEconPerformanceFactual = new TechEconPerformanceFactual();
        createdTechEconPerformanceFactual.setFloorNum(polygonInfo.getFloors());
        createdTechEconPerformanceFactual.setApartsArea(coefficientFactual.getFlatAreaCoeff() * polygonInfo.getArea());
        createdTechEconPerformanceFactual.setCommArea(coefficientFactual.getCommAreaCoeff() * polygonInfo.getArea());
        createdTechEconPerformanceFactual.setDouArea(polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * 0.75f);
        createdTechEconPerformanceFactual.setApartsParkingSpotAmount((int) (polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) / coefficientFactual.getParkingFlatCoeff()));
        createdTechEconPerformanceFactual.setCommParkingSpotAmount((int) (polygonInfo.getArea() * polygonInfo.getCommercialFloors() / coefficientFactual.getParkingCommCoeff()));
        createdTechEconPerformanceFactual.setResidentsNum(coefficientFactual.getFlatAreaCoeff() * polygonInfo.getArea() / coefficientFactual.getResidentsCoeff());
        createdTechEconPerformanceFactual.setDouPlacesNum(polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * 0.8f / coefficientFactual.getDdu10Coeff());
        coefficientFactual.setTechEconPerformanceFactual(createdTechEconPerformanceFactual);
        coefficientFactual.setTepId(createdTechEconPerformanceFactual.getId());

        return coefficientFactualRepository.save(coefficientFactual);
    }

    @Override
    public void deleteCoefficientFactual(int id) {
        coefficientFactualRepository.deleteById(id);
    }

    @Override
    public void saveUpdatedCoefficientFactual(CoefficientFactual coefficientFactual, String id) {

        GeoDataContainer geoDataContainer = projectRepository.getReferenceById(coefficientFactual.getProjectId()).getProjectdata();
        PolygonInfo polygonInfo = geoDataContainer.getPolygonsInfo().stream()
                .filter(polygonInfo1 -> coefficientFactual.getModelId().equals(polygonInfo1.getId()))
                .findAny()
                .orElse(null);

        CoefficientFactual updatedCoefficientFactual = coefficientFactualRepository.findByModelId(id);
        updatedCoefficientFactual.setProjectId(coefficientFactual.getProjectId());
        updatedCoefficientFactual.setModelId(id);
        updatedCoefficientFactual.setTepId(coefficientFactual.getTepId());
        updatedCoefficientFactual.setFlatAreaCoeff(coefficientFactual.getFlatAreaCoeff());
        updatedCoefficientFactual.setCommAreaCoeff(coefficientFactual.getCommAreaCoeff());
        updatedCoefficientFactual.setParkingFlatCoeff(coefficientFactual.getParkingFlatCoeff());
        updatedCoefficientFactual.setParkingCommCoeff(coefficientFactual.getParkingCommCoeff());
        updatedCoefficientFactual.setResidentsCoeff(coefficientFactual.getResidentsCoeff());
        updatedCoefficientFactual.setDdu10Coeff(coefficientFactual.getDdu10Coeff());
        updatedCoefficientFactual.setUtilCoeff(coefficientFactual.getUtilCoeff());

        TechEconPerformanceFactual updatedTechEconPerformanceFactual = techEconPerformanceFactualRepository.getReferenceById(coefficientFactual.getTepId());
        updatedTechEconPerformanceFactual.setApartsArea(coefficientFactual.getFlatAreaCoeff() * polygonInfo.getArea());
        updatedTechEconPerformanceFactual.setCommArea(coefficientFactual.getCommAreaCoeff() * polygonInfo.getArea());
        updatedTechEconPerformanceFactual.setDouArea(polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * 0.75f);
        updatedTechEconPerformanceFactual.setApartsParkingSpotAmount((int) (polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) / coefficientFactual.getParkingFlatCoeff()));
        updatedTechEconPerformanceFactual.setCommParkingSpotAmount((int) (polygonInfo.getArea() * polygonInfo.getCommercialFloors() / coefficientFactual.getParkingCommCoeff()));
        updatedTechEconPerformanceFactual.setResidentsNum(coefficientFactual.getFlatAreaCoeff() * polygonInfo.getArea() / coefficientFactual.getResidentsCoeff());
        updatedTechEconPerformanceFactual.setDouPlacesNum(polygonInfo.getArea() * (polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * 0.8f / coefficientFactual.getDdu10Coeff());
        updatedCoefficientFactual.setTechEconPerformanceFactual(updatedTechEconPerformanceFactual);
        coefficientFactualRepository.save(updatedCoefficientFactual);
    }

    @Override
    public CoefficientFactual getCoefficientFactual(String modelId) {
        return coefficientFactualRepository.findByModelId(modelId);
    }
}
