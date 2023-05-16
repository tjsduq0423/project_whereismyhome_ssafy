package com.whereismyhome.houseinfo.service;

import com.whereismyhome.dongcode.entity.DongCode;
import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import com.whereismyhome.houseinfo.dto.HousePointDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.mapper.HouseInfoMapper;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;
    private final HouseInfoMapper mapper;

    //아파트 정보 조회
    public HouseInfo findHouse(long aptCode) {
        HouseInfo houseInfo = validHouse(aptCode);

        //정보 조회 되었을 때 조회수 1증가
        houseInfo.setViewcount(houseInfo.getViewcount() + 1);
        houseInfoRepository.save(houseInfo);

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

    public long getRank(long aptCode) {
        DongCode gugunName = findGugunName(aptCode);
        long rank = makeRank(gugunName.getSidoName(), gugunName.getGugunName(), aptCode);

        return rank;
    }

    //아파트 코드에 해당하는 지역구 추출
    public DongCode findGugunName(long aptCode) {
        HouseInfo house = validHouse(aptCode);
        DongCode dongCode = house.getDongCode();

        return dongCode;

    }

    //랭크 계산
    public long makeRank(String sidoName, String gugunName,long aptCode) {
        List<Object[]> objectList = houseInfoRepository.makeRank(sidoName, gugunName);

        long rank = 1;
        for (Object[] objects : objectList) {
            if((long)objects[1] == aptCode) break;
            else rank++;
        }

        return rank;
    }
}
