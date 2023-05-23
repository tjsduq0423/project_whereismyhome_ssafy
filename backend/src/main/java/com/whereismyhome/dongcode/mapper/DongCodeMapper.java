package com.whereismyhome.dongcode.mapper;

import com.whereismyhome.dongcode.dto.DonCodeSearchResponseDto;
import com.whereismyhome.dongcode.dto.GuGunAvgMountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DongCodeMapper {

    List<DonCodeSearchResponseDto> dongCodeDataToDonCodeSearchResponseDtos(List<Object[]> dongCodeData);

    default DonCodeSearchResponseDto objectToDonCodeSearchResponseDto(Object[] objects) {
        return DonCodeSearchResponseDto.builder()
                .gugunName(String.valueOf(objects[0]))
                .aptCount((long) objects[1])
                .build();
    }

    List<GuGunAvgMountDto> toGuGunAvgMountDto(List<Object[]> objects);

    default GuGunAvgMountDto objectToDongCodeResponseDto(Object[] objects) {
        return GuGunAvgMountDto.builder()
                .gugunName(String.valueOf(objects[0]))
                .avgMount((long) objects[1])
                .build();
    }
}
