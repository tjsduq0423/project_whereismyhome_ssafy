package com.whereismyhome.amenities.controller;

import com.whereismyhome.amenities.dto.post.GeoPostDto;
import com.whereismyhome.amenities.dto.response.*;
import com.whereismyhome.amenities.mapper.AmenMapper;
import com.whereismyhome.amenities.service.AmenService;
import com.whereismyhome.houseinfo.dto.HousePointDto;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.mapper.HouseInfoMapper;
import com.whereismyhome.houseinfo.service.HouseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/amen")
public class AmenController {
    private final AmenService amenService;
    private final HouseInfoService houseInfoService;
    private final AmenMapper amenMapper;
    private final HouseInfoMapper houseInfoMapper;

    //아파트 전체 조회
    @PostMapping("/house")
    public ResponseEntity findApt(@RequestBody GeoPostDto geoPostDto) {
        List<HouseResponseDto> houseResponseDtos = houseInfoMapper.aptDataListToHouseResponseDtos(amenService.findApt(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel()));

        return ResponseEntity.ok().body(houseResponseDtos);
    }

    //반경 내 cctv 조회
    @PostMapping("/cctv")
    public ResponseEntity findCctv(@RequestBody GeoPostDto geoPostDto) {
        List<CctvResponseDto> cctvList = amenService.findCctv(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel());

        return ResponseEntity.ok().body(cctvList);
    }

    //반경 내 hospital 조회
    @PostMapping("/hospital")
    public ResponseEntity findHospital(@RequestBody GeoPostDto geoPostDto) {
        List<HospitalResponseDto> hospitalResponseDtos = amenMapper.hospitalListToHospitalResponseDtos(amenService.findHospital(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel()));

        return ResponseEntity.ok().body(hospitalResponseDtos);
    }

    //반경 내 school 조회
    @PostMapping("/school")
    public ResponseEntity findSchool(@RequestBody GeoPostDto geoPostDto) {
        List<SchoolResponseDto> schoolResponseDtos = amenMapper.schoolDataListToSchoolResponseDtos(amenService.findSchool(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel()));

        return ResponseEntity.ok().body(schoolResponseDtos);
    }

    //반경 내 bus 조회
    @PostMapping("/bus")
    public ResponseEntity findBus(@RequestBody GeoPostDto geoPostDto) {
        List<BusResponseDto> busResponseDtos = amenMapper.busDataListToBusResponseDtos(amenService.findBus(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel()));
        return ResponseEntity.ok().body(busResponseDtos);
    }

    //반경 내 subway조회
    @PostMapping("/subway")
    public ResponseEntity findSubway(@RequestBody GeoPostDto geoPostDto) {
        List<SubwayResponseDto> subwayResponseDtos = amenMapper.subWayDataListToSubwayResponseDtos(amenService.findSubway(geoPostDto.getLng(), geoPostDto.getLat(), geoPostDto.getZoomLevel()));

        return ResponseEntity.ok().body(subwayResponseDtos);
    }

    //아파트 중심으로 반경 이내
    //버스
    @GetMapping("/bus/{apt-code}")
    public ResponseEntity aptBus(@PathVariable("apt-code") long aptCode) {
        HousePointDto point = houseInfoService.getPoint(aptCode);
        List<BusResponseDto> busResponseDtos = amenMapper.busDataListToBusResponseDtos(amenService.findBus(point.getLng(), point.getLat(), 0));

        return ResponseEntity.ok().body(busResponseDtos);
    }

    //cctv
    @GetMapping("/cctv/{apt-code}")
    public ResponseEntity aptCctv(@PathVariable("apt-code") long aptCode) {
        HousePointDto point = houseInfoService.getPoint(aptCode);
        List<CctvResponseDto> cctvList = amenService.findCctv(point.getLng(), point.getLat(),0);

        return ResponseEntity.ok().body(cctvList);
    }

    //병원
    @GetMapping("/hospital/{apt-code}")
    public ResponseEntity aptHospital(@PathVariable("apt-code") long aptCode) {
        HousePointDto point = houseInfoService.getPoint(aptCode);
        List<HospitalResponseDto> hospitalResponseDtos = amenMapper.hospitalListToHospitalResponseDtos(amenService.findHospital(point.getLng(), point.getLat(), 0));

        return ResponseEntity.ok().body(hospitalResponseDtos);
    }

    //학교
    @GetMapping("/school/{apt-code}")
    public ResponseEntity aptSchool(@PathVariable("apt-code") long aptCode) {
        HousePointDto point = houseInfoService.getPoint(aptCode);
        List<SchoolResponseDto> schoolResponseDtos = amenMapper.schoolDataListToSchoolResponseDtos(amenService.findSchool(point.getLng(), point.getLat(), 0));

        return ResponseEntity.ok().body(schoolResponseDtos);
    }

    //지하철
    @GetMapping("/subway/{apt-code}")
    public ResponseEntity aptSubway(@PathVariable("apt-code") long aptCode) {
        HousePointDto point = houseInfoService.getPoint(aptCode);
        List<SubwayResponseDto> subwayResponseDtos = amenMapper.subWayDataListToSubwayResponseDtos(amenService.findSubway(point.getLng(), point.getLat(), 0));

        return ResponseEntity.ok().body(subwayResponseDtos);
    }
}
