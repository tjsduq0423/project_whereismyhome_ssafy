package com.whereismyhome.houseinfo.service;

import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoInfoService {
    private final HouseInfoRepository houseInfoRepository;

    //반경 내 아파트 정보 조회
    public List<HouseResponseDto> findApt(String lng, String lat) {
        List<HouseResponseDto> aptList = houseInfoRepository.findByApt(lng, lat);

        return aptList;
    }
}
