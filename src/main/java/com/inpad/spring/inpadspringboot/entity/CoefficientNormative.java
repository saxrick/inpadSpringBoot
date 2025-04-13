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
    private long id;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "tep_id")
    private long tepId;

    @Column(name = "flat_area_coeff")
    private float flatAreaCoeff;

    @Column(name = "comm_area_coeff")
    private float commAreaCoeff;

    @Column(name = "ddu10_coeff")
    private float ddu10Coeff;

    @Column(name = "residents_coeff")
    private float residentsCoeff;

    @Column(name = "child_coeff")
    private float childCoeff;

    @Column(name = "school_coeff")
    private float schoolCoeff;

    @Column(name = "ddu25_coeff")
    private float ddu25Coeff;

    @Column(name = "playground_coeff")
    private float playgroundCoeff;

    @Column(name = "sportground_coeff")
    private float sportgroundCoeff;

    @Column(name = "recreation_coeff")
    private float recreationCoeff;

    @Column(name = "util_coeff")
    private float utilCoeff;

    public CoefficientNormative() {
    }

    public CoefficientNormative(long id, int projectId, String modelId, long tepId, float flatAreaCoeff, float commAreaCoeff, float ddu10Coeff, float residentsCoeff, float childCoeff, float schoolCoeff, float ddu25Coeff, float playgroundCoeff, float sportgroundCoeff, float recreationCoeff, float utilCoeff) {
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
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public float getDdu10Coeff() {
        return ddu10Coeff;
    }

    public void setDdu10Coeff(float ddu10Coeff) {
        this.ddu10Coeff = ddu10Coeff;
    }

    public float getResidentsCoeff() {
        return residentsCoeff;
    }

    public void setResidentsCoeff(float residentsCoeff) {
        this.residentsCoeff = residentsCoeff;
    }

    public float getChildCoeff() {
        return childCoeff;
    }

    public void setChildCoeff(float childCoeff) {
        this.childCoeff = childCoeff;
    }

    public float getSchoolCoeff() {
        return schoolCoeff;
    }

    public void setSchoolCoeff(float schoolCoeff) {
        this.schoolCoeff = schoolCoeff;
    }

    public float getDdu25Coeff() {
        return ddu25Coeff;
    }

    public void setDdu25Coeff(float ddu25Coeff) {
        this.ddu25Coeff = ddu25Coeff;
    }

    public float getPlaygroundCoeff() {
        return playgroundCoeff;
    }

    public void setPlaygroundCoeff(float playgroundCoeff) {
        this.playgroundCoeff = playgroundCoeff;
    }

    public float getSportgroundCoeff() {
        return sportgroundCoeff;
    }

    public void setSportgroundCoeff(float sportgroundCoeff) {
        this.sportgroundCoeff = sportgroundCoeff;
    }

    public float getRecreationCoeff() {
        return recreationCoeff;
    }

    public void setRecreationCoeff(float recreationCoeff) {
        this.recreationCoeff = recreationCoeff;
    }

    public float getUtilCoeff() {
        return utilCoeff;
    }

    public void setUtilCoeff(float utilCoeff) {
        this.utilCoeff = utilCoeff;
    }
}
