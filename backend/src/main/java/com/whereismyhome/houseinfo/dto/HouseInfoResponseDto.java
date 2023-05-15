package com.whereismyhome.houseinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfoResponseDto {

    private long aptCode;
    private String buildYear;
    private String roadName;
    private String jibun;
    private String sidoName;
    private String gugunName;
    private String dongName;

}
