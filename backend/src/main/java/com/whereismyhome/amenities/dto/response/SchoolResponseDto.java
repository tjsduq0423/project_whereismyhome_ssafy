package com.whereismyhome.amenities.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolResponseDto {
    private Integer id;

    private String name;

    private String lat;

    private String lng;

    private String dist;
}
