package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data

@Entity
@Table(name = "coefficient_factual")
public class CoefficientFactual{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "tep_id" , insertable=false, updatable=false)
    private Long tepId;

    @Column(name = "flat_area_coeff")
    private double flatAreaCoeff;

    @Column(name = "comm_area_coeff")
    private double commAreaCoeff;

    @Column(name = "parking_flat_coeff")
    private double parkingFlatCoeff;

    @Column(name = "parking_comm_coeff")
    private double parkingCommCoeff;

    @Column(name = "residents_coeff")
    private double residentsCoeff;

    @Column(name = "ddu10_coeff")
    private double ddu10Coeff;

    @Column(name = "util_coeff")
    private double utilCoeff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tep_id")
    private TechEconPerformanceFactual techEconPerformanceFactual;

    public CoefficientFactual() {
    }

    public CoefficientFactual(Long id, int projectId, String modelId, Long tepId, double flatAreaCoeff, double commAreaCoeff, double parkingFlatCoeff, double parkingCommCoeff, double residentsCoeff, double ddu10Coeff, double utilCoeff, TechEconPerformanceFactual techEconPerformanceFactual) {
        this.id = id;
        this.projectId = projectId;
        this.modelId = modelId;
        this.tepId = tepId;
        this.flatAreaCoeff = flatAreaCoeff;
        this.commAreaCoeff = commAreaCoeff;
        this.parkingFlatCoeff = parkingFlatCoeff;
        this.parkingCommCoeff = parkingCommCoeff;
        this.residentsCoeff = residentsCoeff;
        this.ddu10Coeff = ddu10Coeff;
        this.utilCoeff = utilCoeff;
        this.techEconPerformanceFactual = techEconPerformanceFactual;
    }

    @Override
    public String toString() {
        return "CoefficientFactual{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", modelId='" + modelId + '\'' +
                ", tepId=" + tepId +
                ", flatAreaCoeff=" + flatAreaCoeff +
                ", commAreaCoeff=" + commAreaCoeff +
                ", parkingFlatCoeff=" + parkingFlatCoeff +
                ", parkingCommCoeff=" + parkingCommCoeff +
                ", residentsCoeff=" + residentsCoeff +
                ", ddu10Coeff=" + ddu10Coeff +
                ", utilCoeff=" + utilCoeff +
                ", techEconPerformanceFactual=" + techEconPerformanceFactual +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Long getTepId() {
        return tepId;
    }

    public void setTepId(Long tepId) {
        this.tepId = tepId;
    }

    public double getFlatAreaCoeff() {
        return flatAreaCoeff;
    }

    public void setFlatAreaCoeff(double flatAreaCoeff) {
        this.flatAreaCoeff = flatAreaCoeff;
    }

    public double getCommAreaCoeff() {
        return commAreaCoeff;
    }

    public void setCommAreaCoeff(double commAreaCoeff) {
        this.commAreaCoeff = commAreaCoeff;
    }

    public double getParkingFlatCoeff() {
        return parkingFlatCoeff;
    }

    public void setParkingFlatCoeff(double parkingFlatCoeff) {
        this.parkingFlatCoeff = parkingFlatCoeff;
    }

    public double getParkingCommCoeff() {
        return parkingCommCoeff;
    }

    public void setParkingCommCoeff(double parkingCommCoeff) {
        this.parkingCommCoeff = parkingCommCoeff;
    }

    public double getResidentsCoeff() {
        return residentsCoeff;
    }

    public void setResidentsCoeff(double residentsCoeff) {
        this.residentsCoeff = residentsCoeff;
    }

    public double getDdu10Coeff() {
        return ddu10Coeff;
    }

    public void setDdu10Coeff(double ddu10Coeff) {
        this.ddu10Coeff = ddu10Coeff;
    }

    public double getUtilCoeff() {
        return utilCoeff;
    }

    public void setUtilCoeff(double utilCoeff) {
        this.utilCoeff = utilCoeff;
    }

    public TechEconPerformanceFactual getTechEconPerformanceFactual() {
        return techEconPerformanceFactual;
    }

    public void setTechEconPerformanceFactual(TechEconPerformanceFactual techEconPerformanceFactual) {
        this.techEconPerformanceFactual = techEconPerformanceFactual;
    }
}
