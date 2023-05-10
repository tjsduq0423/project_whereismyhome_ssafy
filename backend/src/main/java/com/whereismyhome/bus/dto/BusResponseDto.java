package com.whereismyhome.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusResponseDto {

    private Integer id;

    private String number;

    private String name;

    private String lat;

    private String lng;
}
