package com.whereismyhome.houseinfo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDto {
    private Long aptCode;
    private String apartmentName;
    private String lng;
    private String lat;
    private Integer buildYear;
    private Long avg;
}
