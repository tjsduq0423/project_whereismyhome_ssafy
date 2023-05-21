package com.whereismyhome.houseinfo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RankResponseDto {
    private long rank;
    private long mark;
    private int viewCount;
}
