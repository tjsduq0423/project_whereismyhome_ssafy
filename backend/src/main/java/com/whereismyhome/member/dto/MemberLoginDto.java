package com.whereismyhome.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginDto {
    private String id;
    private String password;
}
