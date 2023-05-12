package com.whereismyhome.amenities.dto.response;

import lombok.Getter;

@Getter
public class SubwayResponseDto {

    private Integer id;

    private String name;

    private String lat;

    private String lng;

    private String phoneNumber;

    public SubwayResponseDto(Integer id, String name, String lat, String lng, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.phoneNumber = phoneNumber;
    }
}
