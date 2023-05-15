package com.whereismyhome.hosedeal.mapper;

import com.whereismyhome.hosedeal.dto.HouseDealResponseDto;
import com.whereismyhome.hosedeal.entity.HouseDeal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseDealMapper {

    @Mapping(source = "houseInfo.aptCode", target = "aptCode")
    HouseDealResponseDto houseDealToHouseDealResponseDto(HouseDeal houseDeal);

    List<HouseDealResponseDto> listToHouseDealResponseDtos(List<HouseDeal> houseDealList);
}
