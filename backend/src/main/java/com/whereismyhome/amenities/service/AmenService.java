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

    //아파트 정보 전체 조회
    public List<Object[]> findApt(String lng, String lat, int zoomLevel) {
        return houseInfoRepository.findByApt(lng, lat, getDist(zoomLevel));
    }


    //반경 내 버스 정보 조회
    public List<Object[]> findBus(String lng, String lat, int zoomLevel) {
        return busRepository.findByBus(lng, lat, getDist(zoomLevel));
    }

    //변경 내 cctv 정보조회
    public List<CctvResponseDto> findCctv(String lng, String lat,int zoomLevel) {

        return cctvRepository.findByCctv(lng, lat, getDist(zoomLevel));
    }

    //변경 내 학교 정보 조회
    public List<Object[]> findSchool(String lng, String lat, int zoomLevel) {

        return schoolRepository.findBySchool(lng, lat, getDist(zoomLevel));
    }

    //반경 내 병원 정보 조회
    public List<Object[]> findHospital(String lng, String lat, int zoomLevel) {

        return hospitalRepository.findByHospital(lng, lat,getDist(zoomLevel));
    }

    //반경 내 지하철 정보조회
    public List<Object[]> findSubway(String lng, String lat, int zoomLevel) {

        return subwayRepository.findBySubway(lng, lat, getDist(zoomLevel));
    }

    //지도 zoomLevel
    public double getDist(int zoomLevel) {

        return switch (zoomLevel) {
            case 1 -> 0.003;
            case 2 -> 0.0075;
            case 3 -> 0.0125;
            default -> 0.01;
        };
    }
}
