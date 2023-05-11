package com.whereismyhome.amenities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CctvResponseDto {

    private Integer id;

    private String usage;

    private String lat;

    private String lng;
}
