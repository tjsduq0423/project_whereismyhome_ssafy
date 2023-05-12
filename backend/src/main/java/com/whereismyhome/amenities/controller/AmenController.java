package com.whereismyhome.amenities.controller;

import com.whereismyhome.amenities.service.AmenService;
import com.whereismyhome.amenities.dto.post.GeoPostDto;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/amen")
public class AmenController {
    private final AmenService amenService;

    //반경 내 아파트 조회
    @PostMapping("/house")
    public ResponseEntity findApt(@RequestBody GeoPostDto geoPostDto) {
        List<HouseResponseDto> aptList = amenService.findApt(geoPostDto.getLng(), geoPostDto.getLat());
        return ResponseEntity.ok().body(aptList);
    }

    //반경 내 cctv 조회

    //반경 내 hospital 조회

    //반경 내 school 조회

    //반경 내 bus 조회

    //반경 내 subway조회
}
