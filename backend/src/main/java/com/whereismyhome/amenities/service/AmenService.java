package com.whereismyhome.amenities.service;

import com.whereismyhome.amenities.dto.response.*;
import com.whereismyhome.amenities.repository.*;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenService {
    private final HouseInfoRepository houseInfoRepository;
    private final BusRepository busRepository;
    private final CctvRepository cctvRepository;
    private final SchoolRepository schoolRepository;
    private final HospitalRepository hospitalRepository;
    private final SubwayRepository subwayRepository;
    //반경 내 아파트 정보 조회
    public List<Object[]> findApt(String lng, String lat) {

        return houseInfoRepository.findByApt(lng, lat);
    }

    //반경 내 버스 정보 조회
    public List<BusResponseDto> findBus(String lng, String lat) {

        return busRepository.findByBus(lng, lat);
    }

    //변경 내 cctv 정보조회
    public List<CctvResponseDto> findCctv(String lng, String lat) {

        return cctvRepository.findByCctv(lng, lat);
    }

    //변경 내 학교 정보 조회
    public List<SchoolResponseDto> findSchool(String lng, String lat) {

        return schoolRepository.findBySchool(lng, lat);
    }

    //반경 내 병원 정보 조회
    public List<HospitalResponseDto> findHospital(String lng, String lat) {

        return hospitalRepository.findByHospital(lng, lat);
    }

    //반경 내 지하철 정보조회
    public List<SubwayResponseDto> findSubway(String lng, String lat) {

        return subwayRepository.findBySubway(lng, lat);
    }
}
