package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tech_econ_performance_factual")
public class TechEconPerformanceFactual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "object_type")
//    private int objectType;
//
//    @Column(name = "object_subtype")
//    private int objectSubtype;

    @Column(name = "floor_num")
    private int floorNum;

    @Column(name = "aparts_area")
    private float apartsArea;

    @Column(name = "comm_area")
    private float commArea;

    @Column(name = "dou_area")
    private float douArea;

    @Column(name = "aparts_parking_spot_amount")
    private int apartsParkingSpotAmount;

    @Column(name = "comm_parking_spot_amount")
    private int commParkingSpotAmount;

    @Column(name = "residents_num")
    private float residentsNum;

    @Column(name = "dou_places_num")
    private float douPacesNum;

    @Column(name = "sou_places_num")
    private float souPlacesNum;

    @Column(name = "total_dou_area")
    private float totalDouArea;

    @Column(name = "total_playground_area")
    private float totalPlaygroundArea;

    @Column(name = "total_sportground_area")
    private float totalSportgroundArea;

    @Column(name = "total_recreation_area")
    private float totalRecreationArea;

    @Column(name = "total_util_area")
    private float totalUtilArea;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_type", referencedColumnName = "id")
    private ObjectType objectType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_subtype", referencedColumnName = "id")
    private ObjectSubType objectSubType;

    public TechEconPerformanceFactual() {
    }

    public TechEconPerformanceFactual(Long id, int floorNum, float apartsArea, float commArea, float douArea, int apartsParkingSpotAmount, int commParkingSpotAmount, float residentsNum, float douPacesNum, float souPlacesNum, float totalDouArea, float totalPlaygroundArea, float totalSportgroundArea, float totalRecreationArea, float totalUtilArea, ObjectType objectType, ObjectSubType objectSubType) {
        this.id = id;
        this.floorNum = floorNum;
        this.apartsArea = apartsArea;
        this.commArea = commArea;
        this.douArea = douArea;
        this.apartsParkingSpotAmount = apartsParkingSpotAmount;
        this.commParkingSpotAmount = commParkingSpotAmount;
        this.residentsNum = residentsNum;
        this.douPacesNum = douPacesNum;
        this.souPlacesNum = souPlacesNum;
        this.totalDouArea = totalDouArea;
        this.totalPlaygroundArea = totalPlaygroundArea;
        this.totalSportgroundArea = totalSportgroundArea;
        this.totalRecreationArea = totalRecreationArea;
        this.totalUtilArea = totalUtilArea;
        this.objectType = objectType;
        this.objectSubType = objectSubType;
    }

    @Override
    public String toString() {
        return "TechEconPerformanceFactual{" +
                "id=" + id +
                ", floorNum=" + floorNum +
                ", apartsArea=" + apartsArea +
                ", commArea=" + commArea +
                ", douArea=" + douArea +
                ", apartsParkingSpotAmount=" + apartsParkingSpotAmount +
                ", commParkingSpotAmount=" + commParkingSpotAmount +
                ", residentsNum=" + residentsNum +
                ", douPacesNum=" + douPacesNum +
                ", souPlacesNum=" + souPlacesNum +
                ", totalDouArea=" + totalDouArea +
                ", totalPlaygroundArea=" + totalPlaygroundArea +
                ", totalSportgroundArea=" + totalSportgroundArea +
                ", totalRecreationArea=" + totalRecreationArea +
                ", totalUtilArea=" + totalUtilArea +
                ", objectType=" + objectType +
                ", objectSubType=" + objectSubType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public float getApartsArea() {
        return apartsArea;
    }

    public void setApartsArea(float apartsArea) {
        this.apartsArea = apartsArea;
    }

    public float getCommArea() {
        return commArea;
    }

    public void setCommArea(float commArea) {
        this.commArea = commArea;
    }

    public float getDouArea() {
        return douArea;
    }

    public void setDouArea(float douArea) {
        this.douArea = douArea;
    }

    public int getApartsParkingSpotAmount() {
        return apartsParkingSpotAmount;
    }

    public void setApartsParkingSpotAmount(int apartsParkingSpotAmount) {
        this.apartsParkingSpotAmount = apartsParkingSpotAmount;
    }

    public int getCommParkingSpotAmount() {
        return commParkingSpotAmount;
    }

    public void setCommParkingSpotAmount(int commParkingSpotAmount) {
        this.commParkingSpotAmount = commParkingSpotAmount;
    }

    public float getResidentsNum() {
        return residentsNum;
    }

    public void setResidentsNum(float residentsNum) {
        this.residentsNum = residentsNum;
    }

    public float getDouPacesNum() {
        return douPacesNum;
    }

    public void setDouPacesNum(float douPacesNum) {
        this.douPacesNum = douPacesNum;
    }

    public float getSouPlacesNum() {
        return souPlacesNum;
    }

    public void setSouPlacesNum(float souPlacesNum) {
        this.souPlacesNum = souPlacesNum;
    }

    public float getTotalDouArea() {
        return totalDouArea;
    }

    public void setTotalDouArea(float totalDouArea) {
        this.totalDouArea = totalDouArea;
    }

    public float getTotalPlaygroundArea() {
        return totalPlaygroundArea;
    }

    public void setTotalPlaygroundArea(float totalPlaygroundArea) {
        this.totalPlaygroundArea = totalPlaygroundArea;
    }

    public float getTotalSportgroundArea() {
        return totalSportgroundArea;
    }

    public void setTotalSportgroundArea(float totalSportgroundArea) {
        this.totalSportgroundArea = totalSportgroundArea;
    }

    public float getTotalRecreationArea() {
        return totalRecreationArea;
    }

    public void setTotalRecreationArea(float totalRecreationArea) {
        this.totalRecreationArea = totalRecreationArea;
    }

    public float getTotalUtilArea() {
        return totalUtilArea;
    }

    public void setTotalUtilArea(float totalUtilArea) {
        this.totalUtilArea = totalUtilArea;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public ObjectSubType getObjectSubType() {
        return objectSubType;
    }

    public void setObjectSubType(ObjectSubType objectSubType) {
        this.objectSubType = objectSubType;
    }
}