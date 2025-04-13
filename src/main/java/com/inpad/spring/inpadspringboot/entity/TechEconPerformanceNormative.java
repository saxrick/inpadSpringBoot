package com.inpad.spring.inpadspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tech_econ_performance_normative")
public class TechEconPerformanceNormative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "object_type")
    private int objectType;

    @Column(name = "object_subtype")
    private int objectSubtype;

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

}