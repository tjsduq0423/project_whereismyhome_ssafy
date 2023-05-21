package com.whereismyhome.houseinfo.service;

import com.whereismyhome.dongcode.entity.DongCode;
import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import com.whereismyhome.houseinfo.dto.HousePointDto;
import com.whereismyhome.houseinfo.dto.RankResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;

    //아파트 정보 조회
    public HouseInfo findHouse(long aptCode) {

        return validHouse(aptCode);
    }

    //유효성 검사
    public HouseInfo validHouse(long aptCode) {

        return houseInfoRepository.findById(aptCode)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.HOUSE_NOT_FOUND));
    }

    //아파트 코드에 맞는 좌표 들고 오기
    public HousePointDto getPoint(long aptCode) {
        HousePointDto point = houseInfoRepository.findByPoint(aptCode);

        return point;
    }

    public RankResponseDto getRank(long aptCode) {
        DongCode gugunName = findGugunName(aptCode);

        return makeRank(gugunName.getSidoName(), gugunName.getGugunName(), aptCode);
    }

    //아파트 코드에 해당하는 지역구 추출
    public DongCode findGugunName(long aptCode) {
        HouseInfo house = validHouse(aptCode);

        return house.getDongCode();
    }

    //랭크 계산
    public RankResponseDto makeRank(String sidoName, String gugunName, long aptCode) {
        List<Object[]> objectList = houseInfoRepository.makeRank(sidoName, gugunName);

        long rank = 1;
        for (Object[] objects : objectList) {
            if ((long) objects[1] == aptCode) {
                return RankResponseDto.builder()
                        .rank(rank)
                        .mark((long) objects[3])
                        .viewCount((int) objects[2])
                        .build();
            } else rank++;
        }
        throw new BusinessLogicException(ExceptionCode.HOUSE_NOT_FOUND);
    }

    //차트에 사용될 데이터
    public List<Object[]> getChartData(String sidoName) {
        List<Object[]> chartList = houseInfoRepository.chartData(sidoName);
        return chartList;
    }

    //조회수 증가
    public void updateViewCount(long aptCode) {
        HouseInfo house = findHouse(aptCode);

        house.setViewcount(house.getViewcount() + 1);
        houseInfoRepository.save(house);
    }
}
