package com.whereismyhome.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkResponseDto {
    private long aptCode;
    private int buildYear;
    private String areaName;
    private long max;
    private long min;
    private String aptName;
}
