package com.whereismyhome.hosedeal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseDealResponseDto {

    private Long no;

    private String dealAmount;

    private int dealYear;

    private int dealMonth;

    private int dealDay;

    private String area;

    private String floor;

    private long aptCode;


}
