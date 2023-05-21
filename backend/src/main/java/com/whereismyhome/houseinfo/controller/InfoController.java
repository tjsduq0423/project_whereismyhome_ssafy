package com.whereismyhome.houseinfo.controller;


import com.whereismyhome.hosedeal.dto.HouseDealResponseDto;
import com.whereismyhome.hosedeal.entity.HouseDeal;
import com.whereismyhome.hosedeal.mapper.HouseDealMapper;
import com.whereismyhome.hosedeal.service.HouseDealService;
import com.whereismyhome.houseinfo.dto.ChartDataDto;
import com.whereismyhome.houseinfo.dto.HouseInfoResponseDto;
import com.whereismyhome.houseinfo.dto.RankResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.mapper.HouseInfoMapper;
import com.whereismyhome.houseinfo.service.HouseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class InfoController {
    private final HouseInfoService houseInfoService;
    private final HouseInfoMapper infoMapper;
    private final HouseDealService houseDealService;
    private final HouseDealMapper houseDealMapper;

    //아파트 정보 조회
    @GetMapping("/{apt-code}")
    public ResponseEntity findHouse(@PathVariable("apt-code") long aptCode) {
        HouseInfo house = houseInfoService.findHouse(aptCode);
        HouseInfoResponseDto houseInfoResponseDto = infoMapper.infoToInfoResponseDto(house);

        return ResponseEntity.ok().body(houseInfoResponseDto);
    }

    //아파트 매매정보 조회
    @GetMapping("/deal/{apt-code}")
    public ResponseEntity findDeal(@PathVariable("apt-code") long aptCode) {
        List<HouseDeal> houseDeal = houseDealService.findHouseDeal(aptCode);
        List<HouseDealResponseDto> houseDealResponseDtos = houseDealMapper.listToHouseDealResponseDtos(houseDeal);

        return ResponseEntity.ok().body(houseDealResponseDtos);
    }

    //랭킹 정보 조회
    @GetMapping("/rank/{apt-code}")
    public ResponseEntity findRank(@PathVariable("apt-code") long aptCode) {
        RankResponseDto rank = houseInfoService.getRank(aptCode);

        return ResponseEntity.ok().body(rank);
    }

    //차트 데이터 조회
    @GetMapping("/chart/{sido-name}")
    public ResponseEntity findChartData(@PathVariable("sido-name") String sidoName) {
        List<Object[]> chartDataList = houseInfoService.getChartData(sidoName);
        List<ChartDataDto> chartDataDtos = infoMapper.chartDataListToChartDataDtos(chartDataList);
        return ResponseEntity.ok().body(chartDataDtos);

    }

    //조회수 증가
    @GetMapping("/view/{apt-code}")
    public ResponseEntity updateViewCount(@PathVariable ("apt-code") long aptCode) {
        houseInfoService.updateViewCount(aptCode);

        return ResponseEntity.ok().body(aptCode + " 조회 수 1 증가");
    }
}
