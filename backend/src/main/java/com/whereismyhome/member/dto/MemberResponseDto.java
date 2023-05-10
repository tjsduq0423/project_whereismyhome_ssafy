package com.whereismyhome.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private String id;
    private String name;
    private String email;
}
