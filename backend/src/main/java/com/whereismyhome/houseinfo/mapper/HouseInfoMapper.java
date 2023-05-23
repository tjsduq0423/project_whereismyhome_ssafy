package com.whereismyhome.houseinfo.mapper;

import com.whereismyhome.houseinfo.dto.ChartDataDto;
import com.whereismyhome.houseinfo.dto.HouseInfoResponseDto;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.dto.HouseSearchResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseInfoMapper {
    @Mapping(source = "dongCode.sidoName", target = "sidoName")
    @Mapping(source = "dongCode.gugunName", target = "gugunName")
    @Mapping(source = "dongCode.dongName", target = "dongName")
    HouseInfoResponseDto infoToInfoResponseDto(HouseInfo houseInfo);

    List<ChartDataDto> chartDataListToChartDataDtos(List<Object[]> chartDataList);

    default ChartDataDto objectToChartData(Object[] objects) {
        return ChartDataDto.builder()
                .gugunName(String.valueOf(objects[0]))
                .markCount((long) objects[1])
                .viewCount((long) objects[2])
                .dealAmount((long) objects[3]).build();
    }

    List<HouseSearchResponseDto> houseSearchDataToHouseSearchResponseDtos(List<Object[]> houseSearchList);

    default HouseSearchResponseDto objectToHouseSearchResponseDto(Object[] objects) {
        return HouseSearchResponseDto.builder()
                .aptCode((long) objects[0])
                .aptName(String.valueOf(objects[1]))
                .bookMarkCount((long) objects[2])
                .build();
    }

    List<HouseResponseDto> aptDataListToHouseResponseDtos(List<Object[]> aptDataList);

    default HouseResponseDto objectToHouseResponseDto(Object[] objects) {
        return HouseResponseDto.builder()
                .aptCode((long) objects[0])
                .apartmentName(String.valueOf(objects[1]))
                .lng(String.valueOf(objects[2]))
                .lat(String.valueOf(objects[3]))
                .buildYear((int) objects[4])
                .avg((long) objects[5])
                .build();
    }
}