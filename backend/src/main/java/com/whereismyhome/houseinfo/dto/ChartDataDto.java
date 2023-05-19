package com.whereismyhome.houseinfo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataDto {
    private String gugunName;
    private long markCount;
    private long viewCount;
    private long dealAmount;
}
