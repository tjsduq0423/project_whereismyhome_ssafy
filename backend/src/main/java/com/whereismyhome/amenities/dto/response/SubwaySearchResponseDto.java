package com.whereismyhome.amenities.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubwaySearchResponseDto {
    private String name;

    public SubwaySearchResponseDto(String name) {
        this.name = name;
    }
}
