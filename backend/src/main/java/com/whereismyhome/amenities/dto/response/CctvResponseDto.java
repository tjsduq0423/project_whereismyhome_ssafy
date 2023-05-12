package com.whereismyhome.amenities.dto.response;

import lombok.Getter;

@Getter
public class CctvResponseDto {

    private Integer id;

    private String usage;

    private String lat;

    private String lng;

    public CctvResponseDto(Integer id, String usage, String lat, String lng) {
        this.id = id;
        this.usage = usage;
        this.lat = lat;
        this.lng = lng;
    }
}
