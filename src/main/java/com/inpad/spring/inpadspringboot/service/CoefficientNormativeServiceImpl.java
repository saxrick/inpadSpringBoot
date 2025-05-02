package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.CoefficientNormative;
import com.inpad.spring.inpadspringboot.entity.GeoDataContainer;
import com.inpad.spring.inpadspringboot.entity.PolygonInfo;
import com.inpad.spring.inpadspringboot.entity.TechEconPerformanceNormative;
import com.inpad.spring.inpadspringboot.repositories.CoefficientNormativeRepository;
import com.inpad.spring.inpadspringboot.repositories.ProjectRepository;
import com.inpad.spring.inpadspringboot.repositories.TechEconPerformanceNormativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CoefficientNormativeServiceImpl implements CoefficientNormativeService{

    @Autowired
    private final CoefficientNormativeRepository coefficientNormativeRepository;

    @Autowired
    private final TechEconPerformanceNormativeRepository techEconPerformanceNormativeRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public CoefficientNormative saveCoefficientNormative(CoefficientNormative coefficientNormative) {
        GeoDataContainer geoDataContainer = projectRepository.getReferenceById(coefficientNormative.getProjectId()).getProjectdata();
        PolygonInfo polygonInfo = geoDataContainer.getPolygonsInfo().stream()
                .filter(polygonInfo1 -> coefficientNormative.getModelId().equals(polygonInfo1.getId()))
                .findAny()
                .orElse(null);
        TechEconPerformanceNormative createdTechEconPerformanceNormative = new TechEconPerformanceNormative();
        createdTechEconPerformanceNormative.setFloorNum(polygonInfo.getFloors());
        createdTechEconPerformanceNormative.setApartsArea((polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * polygonInfo.getArea() * coefficientNormative.getFlatAreaCoeff());
        createdTechEconPerformanceNormative.setCommArea(polygonInfo.getArea() * coefficientNormative.getCommAreaCoeff());
        createdTechEconPerformanceNormative.setApartsParkingSpotAmount((int) (createdTechEconPerformanceNormative.getApartsArea()/ 60 * 1.2f));
        createdTechEconPerformanceNormative.setCommParkingSpotAmount((int) (createdTechEconPerformanceNormative.getCommArea() / 60));
        createdTechEconPerformanceNormative.setResidentsNum(createdTechEconPerformanceNormative.getApartsArea() / coefficientNormative.getResidentsCoeff());
        createdTechEconPerformanceNormative.setDouPlacesNum(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getChildCoeff());
        createdTechEconPerformanceNormative.setDouArea(createdTechEconPerformanceNormative.getDouPlacesNum() * coefficientNormative.getDdu10Coeff());
        createdTechEconPerformanceNormative.setSouPlacesNum(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getSchoolCoeff());
        createdTechEconPerformanceNormative.setTotalDouArea(createdTechEconPerformanceNormative.getDouPlacesNum() * coefficientNormative.getDdu25Coeff());
        createdTechEconPerformanceNormative.setTotalPlaygroundArea(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getPlaygroundCoeff());
        createdTechEconPerformanceNormative.setTotalSportgroundArea(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getSportgroundCoeff());
        createdTechEconPerformanceNormative.setTotalRecreationArea(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getRecreationCoeff());
        createdTechEconPerformanceNormative.setTotalRecreationArea(createdTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getUtilCoeff());
        coefficientNormative.setTechEconPerformanceNormative(createdTechEconPerformanceNormative);
//        coefficientNormative.setTepId(createdTechEconPerformanceNormative.getId());
        return coefficientNormativeRepository.save(coefficientNormative);
    }

    @Override
    public void deleteCoefficientNormative(int id) {
        coefficientNormativeRepository.deleteById(id);
    }

    @Override
    public void saveUpdatedCoefficientNormative(CoefficientNormative coefficientNormative, String id) {
        GeoDataContainer geoDataContainer = projectRepository.getReferenceById(coefficientNormative.getProjectId()).getProjectdata();
        PolygonInfo polygonInfo = geoDataContainer.getPolygonsInfo().stream()
                .filter(polygonInfo1 -> coefficientNormative.getModelId().equals(polygonInfo1.getId()))
                .findAny()
                .orElse(null);

        CoefficientNormative updatedCoefficientNormative = coefficientNormativeRepository.findByModelId(id);
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

        TechEconPerformanceNormative updatedTechEconPerformanceNormative = techEconPerformanceNormativeRepository.getReferenceById(coefficientNormative.getTepId());
        updatedTechEconPerformanceNormative.setFloorNum(polygonInfo.getFloors());
        updatedTechEconPerformanceNormative.setApartsArea((polygonInfo.getFloors() - polygonInfo.getCommercialFloors()) * polygonInfo.getArea() * coefficientNormative.getFlatAreaCoeff());
        updatedTechEconPerformanceNormative.setCommArea(polygonInfo.getArea() * coefficientNormative.getCommAreaCoeff());
        updatedTechEconPerformanceNormative.setApartsParkingSpotAmount((int) (updatedTechEconPerformanceNormative.getApartsArea()/ 60 * 1.2f));
        updatedTechEconPerformanceNormative.setCommParkingSpotAmount((int) (updatedTechEconPerformanceNormative.getCommArea() / 60));
        updatedTechEconPerformanceNormative.setResidentsNum(updatedTechEconPerformanceNormative.getApartsArea() / coefficientNormative.getResidentsCoeff());
        updatedTechEconPerformanceNormative.setDouPlacesNum(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getChildCoeff());
        updatedTechEconPerformanceNormative.setDouArea(updatedTechEconPerformanceNormative.getDouPlacesNum() * coefficientNormative.getDdu10Coeff());
        updatedTechEconPerformanceNormative.setSouPlacesNum(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getSchoolCoeff());
        updatedTechEconPerformanceNormative.setTotalDouArea(updatedTechEconPerformanceNormative.getDouPlacesNum() * coefficientNormative.getDdu25Coeff());
        updatedTechEconPerformanceNormative.setTotalPlaygroundArea(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getPlaygroundCoeff());
        updatedTechEconPerformanceNormative.setTotalSportgroundArea(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getSportgroundCoeff());
        updatedTechEconPerformanceNormative.setTotalRecreationArea(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getRecreationCoeff());
        updatedTechEconPerformanceNormative.setTotalRecreationArea(updatedTechEconPerformanceNormative.getResidentsNum() * coefficientNormative.getUtilCoeff());
        coefficientNormative.setTechEconPerformanceNormative(updatedTechEconPerformanceNormative);
        coefficientNormativeRepository.save(updatedCoefficientNormative);
    }

    @Override
    public CoefficientNormative getCoefficientNormative(String id) {
        return coefficientNormativeRepository.findByModelId(id);
    }
}
