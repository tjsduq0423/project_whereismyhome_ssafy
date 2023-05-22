package com.whereismyhome.amenities.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusResponseDto {

    private Integer id;

    private String name;

    private String lat;

    private String lng;

    private String dist;
}
