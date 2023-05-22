package com.whereismyhome.houseinfo.entity;

import com.whereismyhome.dongcode.entity.DongCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.Point;


@Entity
@Table(name = "houseinfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {
    @Id
    private Long aptCode;

    @Column
    private int buildYear;

    @Column
    private String roadName;

    @Column
    private String roadNameBonbun;

    @Column
    private String roadNameBubun;

    @Column
    private String roadNameSeq;

    @Column
    private String roadNameBasementCode;

    @Column
    private String roadNameCode;

    @Column
    private String dong;

    @Column
    private String bonbun;

    @Column
    private String sigunguCode;

    @Column
    private String eubmyundongCode;

    @Column
    private String landCode;

    @Column
    private String apartmentName;

    @Column
    private String jibun;

    @Column
    private String lng;

    @Column
    private String lat;

    @Column
    private int viewcount;

    @Column
    private Long avg;

    @Column
    private Point localPoint;

    @ManyToOne
    @JoinColumn(name = "dong_code")
    private DongCode dongCode;
}
