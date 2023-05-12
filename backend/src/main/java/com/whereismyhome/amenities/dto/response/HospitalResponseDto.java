package com.whereismyhome.amenities.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class HospitalResponseDto {

    private Integer id;


    private String name;


    private String phoneNumber;


    private String lng;


    private String lat;

    public HospitalResponseDto(Integer id, String name, String phoneNumber, String lng, String lat) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lng = lng;
        this.lat = lat;
    }
}

