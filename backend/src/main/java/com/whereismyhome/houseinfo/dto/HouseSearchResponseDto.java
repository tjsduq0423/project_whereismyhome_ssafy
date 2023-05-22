package com.whereismyhome.houseinfo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseSearchResponseDto {
    private long aptCode;
    private String aptName;
    private long bookMarkCount;

}
