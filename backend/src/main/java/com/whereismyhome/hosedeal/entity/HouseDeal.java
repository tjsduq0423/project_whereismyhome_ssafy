package com.whereismyhome.hosedeal.entity;

import com.whereismyhome.houseinfo.entity.HouseInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "housedeal")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "dealAmount")
    private String dealAmount;

    @Column(name = "dealYear")
    private int dealYear;

    @Column(name = "dealMonth")
    private int dealMonth;

    @Column(name = "dealDay")
    private int dealDay;

    @Column(name = "area")
    private String area;

    @Column(name = "floor")
    private String floor;

    @Column(name = "cancelDealType")
    private String cancelDealType;

    @ManyToOne
    @JoinColumn(name = "apt_code")
    private HouseInfo houseInfo;
}
