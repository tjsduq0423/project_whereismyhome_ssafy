package com.whereismyhome.amenities.service;

import com.whereismyhome.amenities.dto.response.BusResponseDto;
import com.whereismyhome.amenities.dto.response.CctvResponseDto;
import com.whereismyhome.amenities.repository.BusRepository;
import com.whereismyhome.amenities.repository.CctvRepository;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
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

    //반경 내 아파트 정보 조회
    public List<HouseResponseDto> findApt(String lng, String lat) {
        List<HouseResponseDto> aptList = houseInfoRepository.findByApt(lng, lat);

        return aptList;
    }

    //반경 내 버스 정보 조회
    public List<BusResponseDto> findBus(String lng, String lat) {
        List<BusResponseDto> busList = busRepository.findByBus(lng, lat);

        return busList;
    }

    //변경 내 cctv 정보조회
    public List<CctvResponseDto> findCctv(String lng, String lat) {
        List<CctvResponseDto> cctvList = cctvRepository.findByCctv(lng, lat);

        return cctvList;
    }
}
