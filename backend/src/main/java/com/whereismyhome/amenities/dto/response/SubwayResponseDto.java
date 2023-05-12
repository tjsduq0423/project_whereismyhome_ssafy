package com.whereismyhome.amenities.dto.response;

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
