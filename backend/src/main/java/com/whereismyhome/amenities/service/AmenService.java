package com.whereismyhome.amenities.service;

import com.whereismyhome.amenities.dto.response.BusResponseDto;
import com.whereismyhome.amenities.repository.BusRepository;
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
}
