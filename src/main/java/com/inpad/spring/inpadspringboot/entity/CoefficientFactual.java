package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coefficient_factual")
public class CoefficientFactual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "tep_id", insertable=false, updatable=false)
    private Long tepId;

    @Column(name = "flat_area_coeff")
    private float flatAreaCoeff;

    @Column(name = "comm_area_coeff")
    private float commAreaCoeff;

    @Column(name = "parking_flat_coeff")
    private float parkingFlatCoeff;

    @Column(name = "parking_comm_coeff")
    private float parkingCommCoeff;

    @Column(name = "residents_coeff")
    private float residentsCoeff;

    @Column(name = "ddu10_coeff")
    private float ddu10Coeff;

    @Column(name = "util_coeff")
    private float utilCoeff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tep_id", referencedColumnName = "id")
    private TechEconPerformanceFactual techEconPerformanceFactual;

    public CoefficientFactual() {
    }

    public CoefficientFactual(Long id, int projectId, String modelId, Long tepId, float flatAreaCoeff, float commAreaCoeff, float parkingFlatCoeff, float parkingCommCoeff, float residentsCoeff, float ddu10Coeff, float utilCoeff, TechEconPerformanceFactual techEconPerformanceFactual) {
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

    public float getFlatAreaCoeff() {
        return flatAreaCoeff;
    }

    public void setFlatAreaCoeff(float flatAreaCoeff) {
        this.flatAreaCoeff = flatAreaCoeff;
    }

    public float getCommAreaCoeff() {
        return commAreaCoeff;
    }

    public void setCommAreaCoeff(float commAreaCoeff) {
        this.commAreaCoeff = commAreaCoeff;
    }

    public float getParkingFlatCoeff() {
        return parkingFlatCoeff;
    }

    public void setParkingFlatCoeff(float parkingFlatCoeff) {
        this.parkingFlatCoeff = parkingFlatCoeff;
    }

    public float getParkingCommCoeff() {
        return parkingCommCoeff;
    }

    public void setParkingCommCoeff(float parkingCommCoeff) {
        this.parkingCommCoeff = parkingCommCoeff;
    }

    public float getResidentsCoeff() {
        return residentsCoeff;
    }

    public void setResidentsCoeff(float residentsCoeff) {
        this.residentsCoeff = residentsCoeff;
    }

    public float getDdu10Coeff() {
        return ddu10Coeff;
    }

    public void setDdu10Coeff(float ddu10Coeff) {
        this.ddu10Coeff = ddu10Coeff;
    }

    public float getUtilCoeff() {
        return utilCoeff;
    }

    public void setUtilCoeff(float utilCoeff) {
        this.utilCoeff = utilCoeff;
    }

    public TechEconPerformanceFactual getTechEconPerformanceFactual() {
        return techEconPerformanceFactual;
    }

    public void setTechEconPerformanceFactual(TechEconPerformanceFactual techEconPerformanceFactual) {
        this.techEconPerformanceFactual = techEconPerformanceFactual;
    }
}
