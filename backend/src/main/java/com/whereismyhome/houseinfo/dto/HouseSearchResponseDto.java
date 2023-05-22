package com.whereismyhome.houseinfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseSearchResponseDto {
    private long aptCode;
    private String aptName;
    private long bookMarkCount;

    public HouseSearchResponseDto(long aptCode, String aptName, long bookMarkCount) {
        this.aptCode = aptCode;
        this.aptName = aptName;
        this.bookMarkCount = bookMarkCount;
    }
}
