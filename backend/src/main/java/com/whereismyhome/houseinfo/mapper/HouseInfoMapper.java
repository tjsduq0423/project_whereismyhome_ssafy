package com.whereismyhome.houseinfo.mapper;

import com.whereismyhome.houseinfo.dto.HouseInfoResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HouseInfoMapper {
    @Mapping(source = "dongCode.sidoName", target = "sidoName")
    @Mapping(source = "dongCode.gugunName",target = "gugunName")
    @Mapping(source = "dongCode.dongName",target = "dongName")
    HouseInfoResponseDto infoToInfoResponseDto(HouseInfo houseInfo);
}
