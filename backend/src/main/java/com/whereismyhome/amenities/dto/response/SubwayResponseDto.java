package com.whereismyhome.amenities.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubwayResponseDto {

    private Integer id;

    private String name;

    private String lat;

    private String lng;

    private String phoneNumber;

    private String dist;
}
