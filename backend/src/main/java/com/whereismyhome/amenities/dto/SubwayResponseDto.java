package com.whereismyhome.amenities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubwayResponseDto {

    private Integer id;

    private String name;

    private String lat;

    private String lng;

    private String phoneNumber;
}
