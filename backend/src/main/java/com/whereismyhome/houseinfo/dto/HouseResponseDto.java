package com.whereismyhome.houseinfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseResponseDto {
    private Long aptCode;
    private String apartmentName;
    private String lng;
    private String lat;

    public HouseResponseDto(Long aptCode, String apartmentName, String lng, String lat) {
        this.aptCode = aptCode;
        this.apartmentName = apartmentName;
        this.lng = lng;
        this.lat = lat;
    }
}
