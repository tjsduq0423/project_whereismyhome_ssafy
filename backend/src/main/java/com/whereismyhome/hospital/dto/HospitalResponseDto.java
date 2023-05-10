package com.whereismyhome.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalResponseDto {

    private Integer id;


    private String name;


    private String phoneNumber;


    private String lng;


    private String lat;
}

