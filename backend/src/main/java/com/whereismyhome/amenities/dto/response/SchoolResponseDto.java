package com.whereismyhome.amenities.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolResponseDto {
    private Integer id;

    private String name;

    private String lat;

    private String lng;
}
