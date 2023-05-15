package com.whereismyhome.houseinfo.service;

import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
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
}
