package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "coefficient_normative")
public class CoefficientNormative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "tep_id", insertable=false, updatable=false)
    private long tepId;

    @Column(name = "flat_area_coeff")
    private double flatAreaCoeff;

    @Column(name = "comm_area_coeff")
    private double commAreaCoeff;

    @Column(name = "ddu10_coeff")
    private double ddu10Coeff;

    @Column(name = "residents_coeff")
    private double residentsCoeff;

    @Column(name = "child_coeff")
    private double childCoeff;

    @Column(name = "school_coeff")
    private double schoolCoeff;

    @Column(name = "ddu25_coeff")
    private double ddu25Coeff;

    @Column(name = "playground_coeff")
    private double playgroundCoeff;

    @Column(name = "sportground_coeff")
    private double sportgroundCoeff;

    @Column(name = "recreation_coeff")
    private double recreationCoeff;

    @Column(name = "util_coeff")
    private double utilCoeff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tep_id")
    private TechEconPerformanceNormative techEconPerformanceNormative;

    public CoefficientNormative() {
    }

    public CoefficientNormative(Long id, int projectId, String modelId, long tepId, double flatAreaCoeff, double commAreaCoeff, double ddu10Coeff, double residentsCoeff, double childCoeff, double schoolCoeff, double ddu25Coeff, double playgroundCoeff, double sportgroundCoeff, double recreationCoeff, double utilCoeff, TechEconPerformanceNormative techEconPerformanceNormative) {
        this.id = id;
        this.projectId = projectId;
        this.modelId = modelId;
        this.tepId = tepId;
        this.flatAreaCoeff = flatAreaCoeff;
        this.commAreaCoeff = commAreaCoeff;
        this.ddu10Coeff = ddu10Coeff;
        this.residentsCoeff = residentsCoeff;
        this.childCoeff = childCoeff;
        this.schoolCoeff = schoolCoeff;
        this.ddu25Coeff = ddu25Coeff;
        this.playgroundCoeff = playgroundCoeff;
        this.sportgroundCoeff = sportgroundCoeff;
        this.recreationCoeff = recreationCoeff;
        this.utilCoeff = utilCoeff;
        this.techEconPerformanceNormative = techEconPerformanceNormative;
    }

    @Override
    public String toString() {
        return "CoefficientNormative{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", modelId='" + modelId + '\'' +
                ", tepId=" + tepId +
                ", flatAreaCoeff=" + flatAreaCoeff +
                ", commAreaCoeff=" + commAreaCoeff +
                ", ddu10Coeff=" + ddu10Coeff +
                ", residentsCoeff=" + residentsCoeff +
                ", childCoeff=" + childCoeff +
                ", schoolCoeff=" + schoolCoeff +
                ", ddu25Coeff=" + ddu25Coeff +
                ", playgroundCoeff=" + playgroundCoeff +
                ", sportgroundCoeff=" + sportgroundCoeff +
                ", recreationCoeff=" + recreationCoeff +
                ", utilCoeff=" + utilCoeff +
                ", techEconPerformanceNormative=" + techEconPerformanceNormative +
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

    public long getTepId() {
        return tepId;
    }

    public void setTepId(long tepId) {
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

    public double getDdu10Coeff() {
        return ddu10Coeff;
    }

    public void setDdu10Coeff(double ddu10Coeff) {
        this.ddu10Coeff = ddu10Coeff;
    }

    public double getResidentsCoeff() {
        return residentsCoeff;
    }

    public void setResidentsCoeff(double residentsCoeff) {
        this.residentsCoeff = residentsCoeff;
    }

    public double getChildCoeff() {
        return childCoeff;
    }

    public void setChildCoeff(double childCoeff) {
        this.childCoeff = childCoeff;
    }

    public double getSchoolCoeff() {
        return schoolCoeff;
    }

    public void setSchoolCoeff(double schoolCoeff) {
        this.schoolCoeff = schoolCoeff;
    }

    public double getDdu25Coeff() {
        return ddu25Coeff;
    }

    public void setDdu25Coeff(double ddu25Coeff) {
        this.ddu25Coeff = ddu25Coeff;
    }

    public double getPlaygroundCoeff() {
        return playgroundCoeff;
    }

    public void setPlaygroundCoeff(double playgroundCoeff) {
        this.playgroundCoeff = playgroundCoeff;
    }

    public double getSportgroundCoeff() {
        return sportgroundCoeff;
    }

    public void setSportgroundCoeff(double sportgroundCoeff) {
        this.sportgroundCoeff = sportgroundCoeff;
    }

    public double getRecreationCoeff() {
        return recreationCoeff;
    }

    public void setRecreationCoeff(double recreationCoeff) {
        this.recreationCoeff = recreationCoeff;
    }

    public double getUtilCoeff() {
        return utilCoeff;
    }

    public void setUtilCoeff(double utilCoeff) {
        this.utilCoeff = utilCoeff;
    }

    public TechEconPerformanceNormative getTechEconPerformanceNormative() {
        return techEconPerformanceNormative;
    }

    public void setTechEconPerformanceNormative(TechEconPerformanceNormative techEconPerformanceNormative) {
        this.techEconPerformanceNormative = techEconPerformanceNormative;
    }
}