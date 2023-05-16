package com.whereismyhome.houseinfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HousePointDto {
    private String lng;
    private String lat;

    public HousePointDto(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
