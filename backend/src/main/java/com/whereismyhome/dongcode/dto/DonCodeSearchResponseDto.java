package com.whereismyhome.dongcode.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonCodeSearchResponseDto {
    private String gugunName;
    private Long aptCount;

}
