package com.whereismyhome.amenities.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalResponseDto {

    private Integer id;

    private String name;

    private String phoneNumber;

    private String lng;

    private String lat;

    private String dist;
}

