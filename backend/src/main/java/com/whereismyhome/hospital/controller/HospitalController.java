package com.whereismyhome.hospital.controller;

import com.whereismyhome.hospital.dto.HospitalResponseDto;
import com.whereismyhome.hospital.mapper.HospitalMapper;
import com.whereismyhome.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {
    private final HospitalService hospitalService;
    private final HospitalMapper mapper;

    @GetMapping("/list")
    public ResponseEntity findAll(){
        List<HospitalResponseDto> hospitalResponseDtos = mapper.hospitalListToHospitalResponseDtos(hospitalService.findAll());
        return ResponseEntity.ok().body(hospitalResponseDtos);
    }
}
