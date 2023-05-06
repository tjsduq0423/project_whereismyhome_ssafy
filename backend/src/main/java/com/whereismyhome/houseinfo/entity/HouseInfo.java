package com.whereismyhome.houseinfo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "houseinfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {
    @Id
    private Long aptCode;
}
