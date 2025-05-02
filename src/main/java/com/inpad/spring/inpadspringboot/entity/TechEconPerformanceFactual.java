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
    private double apartsArea;

    @Column(name = "comm_area")
    private double commArea;

    @Column(name = "dou_area")
    private double douArea;

    @Column(name = "aparts_parking_spot_amount")
    private int apartsParkingSpotAmount;

    @Column(name = "comm_parking_spot_amount")
    private int commParkingSpotAmount;

    @Column(name = "residents_num")
    private double residentsNum;

    @Column(name = "dou_places_num")
    private double douPlacesNum;

    @Column(name = "sou_places_num")
    private double souPlacesNum;

    @Column(name = "total_dou_area")
    private double totalDouArea;

    @Column(name = "total_playground_area")
    private double totalPlaygroundArea;

    @Column(name = "total_sportground_area")
    private double totalSportgroundArea;

    @Column(name = "total_recreation_area")
    private double totalRecreationArea;

    @Column(name = "total_util_area")
    private double totalUtilArea;

    public TechEconPerformanceFactual() {
    }

    public TechEconPerformanceFactual(Long id, int floorNum, double apartsArea, double commArea, double douArea, int apartsParkingSpotAmount, int commParkingSpotAmount, double residentsNum, double douPlacesNum, double souPlacesNum, double totalDouArea, double totalPlaygroundArea, double totalSportgroundArea, double totalRecreationArea, double totalUtilArea) {
        this.id = id;
        this.floorNum = floorNum;
        this.apartsArea = apartsArea;
        this.commArea = commArea;
        this.douArea = douArea;
        this.apartsParkingSpotAmount = apartsParkingSpotAmount;
        this.commParkingSpotAmount = commParkingSpotAmount;
        this.residentsNum = residentsNum;
        this.douPlacesNum = douPlacesNum;
        this.souPlacesNum = souPlacesNum;
        this.totalDouArea = totalDouArea;
        this.totalPlaygroundArea = totalPlaygroundArea;
        this.totalSportgroundArea = totalSportgroundArea;
        this.totalRecreationArea = totalRecreationArea;
        this.totalUtilArea = totalUtilArea;
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
                ", douPlacesNum=" + douPlacesNum +
                ", souPlacesNum=" + souPlacesNum +
                ", totalDouArea=" + totalDouArea +
                ", totalPlaygroundArea=" + totalPlaygroundArea +
                ", totalSportgroundArea=" + totalSportgroundArea +
                ", totalRecreationArea=" + totalRecreationArea +
                ", totalUtilArea=" + totalUtilArea +
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

    public double getApartsArea() {
        return apartsArea;
    }

    public void setApartsArea(double apartsArea) {
        this.apartsArea = apartsArea;
    }

    public double getCommArea() {
        return commArea;
    }

    public void setCommArea(double commArea) {
        this.commArea = commArea;
    }

    public double getDouArea() {
        return douArea;
    }

    public void setDouArea(double douArea) {
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

    public double getResidentsNum() {
        return residentsNum;
    }

    public void setResidentsNum(double residentsNum) {
        this.residentsNum = residentsNum;
    }

    public double getDouPlacesNum() {
        return douPlacesNum;
    }

    public void setDouPlacesNum(double douPlacesNum) {
        this.douPlacesNum = douPlacesNum;
    }

    public double getSouPlacesNum() {
        return souPlacesNum;
    }

    public void setSouPlacesNum(double souPlacesNum) {
        this.souPlacesNum = souPlacesNum;
    }

    public double getTotalDouArea() {
        return totalDouArea;
    }

    public void setTotalDouArea(double totalDouArea) {
        this.totalDouArea = totalDouArea;
    }

    public double getTotalPlaygroundArea() {
        return totalPlaygroundArea;
    }

    public void setTotalPlaygroundArea(double totalPlaygroundArea) {
        this.totalPlaygroundArea = totalPlaygroundArea;
    }

    public double getTotalSportgroundArea() {
        return totalSportgroundArea;
    }

    public void setTotalSportgroundArea(double totalSportgroundArea) {
        this.totalSportgroundArea = totalSportgroundArea;
    }

    public double getTotalRecreationArea() {
        return totalRecreationArea;
    }

    public void setTotalRecreationArea(double totalRecreationArea) {
        this.totalRecreationArea = totalRecreationArea;
    }

    public double getTotalUtilArea() {
        return totalUtilArea;
    }

    public void setTotalUtilArea(double totalUtilArea) {
        this.totalUtilArea = totalUtilArea;
    }
}