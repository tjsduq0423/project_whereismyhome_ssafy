package com.whereismyhome.houseinfo.service;

import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import com.whereismyhome.houseinfo.dto.HousePointDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;

    //아파트 정보 조회
    public HouseInfo findHouse(long aptCode) {
        HouseInfo houseInfo = validHouse(aptCode);

        return houseInfo;
    }

    //유효성 검사
    public HouseInfo validHouse(long aptCode) {
        HouseInfo houseInfo = houseInfoRepository.findById(aptCode)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.HOUSE_NOT_FOUND));

        return houseInfo;
    }

    //아파트 코드에 맞는 좌표 들고 오기
    public HousePointDto getPoint(long aptCode) {
        HousePointDto point = houseInfoRepository.findByPoint(aptCode);

        return point;
    }
}
