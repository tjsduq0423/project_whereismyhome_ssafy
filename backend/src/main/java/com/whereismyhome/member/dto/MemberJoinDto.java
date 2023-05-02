package com.whereismyhome.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {
    private String id;
    private String password;
    private String name;
    private String email;
}
