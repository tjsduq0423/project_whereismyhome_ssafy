package com.whereismyhome.houseinfo.controller;

import com.whereismyhome.houseinfo.dto.HousePostDto;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.service.GeoInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/geo")
public class GeoController {
    private final GeoInfoService geoInfoService;

    //반경 내 아파트 조회
    @PostMapping("/house")
    public ResponseEntity findApt(@RequestBody HousePostDto housePostDto) {
        List<HouseResponseDto> aptList = geoInfoService.findApt(housePostDto.getLng(), housePostDto.getLat());
        return ResponseEntity.ok().body(aptList);
    }

    //반경 내 cctv 조회

    //반경 내 hospital 조회

    //반경 내 school 조회

    //반경 내 bus 조회

    //반경 내 subway조회

}
