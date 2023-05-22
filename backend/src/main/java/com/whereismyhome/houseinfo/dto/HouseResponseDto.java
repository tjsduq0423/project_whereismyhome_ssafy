package com.whereismyhome.houseinfo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class HouseResponseDto {
    private Long aptCode;
    private String apartmentName;
    private String lng;
    private String lat;
    private Integer buildYear;
    private Long avg;

    public HouseResponseDto(Long aptCode, String apartmentName, String lng, String lat, Integer buildYear, Long avg) {
        this.aptCode = aptCode;
        this.apartmentName = apartmentName;
        this.lng = lng;
        this.lat = lat;
        this.buildYear = buildYear;
        this.avg = avg;
    }
}
