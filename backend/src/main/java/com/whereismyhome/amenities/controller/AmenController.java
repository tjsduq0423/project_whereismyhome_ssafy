package com.whereismyhome.amenities.controller;

import com.whereismyhome.amenities.dto.*;
import com.whereismyhome.amenities.mapper.AmenMapper;
import com.whereismyhome.amenities.service.AmenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/amen")
public class AmenController {

    private final AmenService amenService;

    private final AmenMapper mapper;
    @GetMapping("/subway")
    public ResponseEntity findSubway(){
        List<SubwayResponseDto> subwayResponseDtos = mapper.subwayListToSubwayResponseDto(amenService.findSubway());

        return ResponseEntity.ok().body(subwayResponseDtos);
    }

    @GetMapping("/school")
    public ResponseEntity findSchool() {
        List<SchoolResponseDto> schoolResponseDtos = mapper.schoolListToSchoolResponseDtos(amenService.findSchool());

        return ResponseEntity.ok().body(schoolResponseDtos);
    }

    @GetMapping("/bus")
    public ResponseEntity findBus(){
        List<BusResponseDto> busResponseDtos = mapper.busListToBusResponseDtos(amenService.findBus());

        return ResponseEntity.ok().body(busResponseDtos);
    }

    @GetMapping("/hospital")
    public ResponseEntity findHospital(){
        List<HospitalResponseDto> hospitalResponseDtos = mapper.hospitalListToHospitalResponseDtos(amenService.findHospital());
        return ResponseEntity.ok().body(hospitalResponseDtos);
    }

    @GetMapping("/cctv")
    public ResponseEntity findCctv(){
        List<CctvResponseDto> cctvResponseDtos = mapper.cctvListToCctvResponseDtos(amenService.findCctv());

        return ResponseEntity.ok().body(cctvResponseDtos);
    }
}
