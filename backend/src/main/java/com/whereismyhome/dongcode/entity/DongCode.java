package com.whereismyhome.dongcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "dongcode")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DongCode {
    @Id
    private String dongCode;

    @Column(name = "sidoName")
    private String sidoName;

    @Column(name = "gugunName")
    private String gugunName;

    @Column(name = "dongName")
    private String dongName;
}
