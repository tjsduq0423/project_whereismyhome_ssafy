package com.whereismyhome.dongcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuGunAvgMountDto {
    private String gugunName;
    private long avgMount;
}
