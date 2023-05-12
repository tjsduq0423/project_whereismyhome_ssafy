package com.whereismyhome.amenities.dto.response;

import lombok.Getter;

@Getter
public class BusResponseDto {

    private Integer id;

    private String name;

    private String lat;

    private String lng;

    public BusResponseDto(Integer id, String name, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
