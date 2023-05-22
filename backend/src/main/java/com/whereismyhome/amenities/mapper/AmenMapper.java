package com.whereismyhome.amenities.mapper;

import com.whereismyhome.amenities.dto.response.BusResponseDto;
import com.whereismyhome.amenities.dto.response.SubwayResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AmenMapper {

    List<BusResponseDto> busDataListToBusResponseDtos(List<Object[]> busDataList);

    default BusResponseDto objectToBusResponseDto(Object[] objects){
        return BusResponseDto.builder()
                .id((int) objects[0])
                .name(String.valueOf(objects[1]))
                .lng(String.valueOf(objects[2]))
                .lat(String.valueOf(objects[3]))
                .dist((objects[4]) + "m")
                .build();
    }

    List<SubwayResponseDto> subWayDataListToSubwayResponseDtos(List<Object[]> subDataList);

    default SubwayResponseDto objectToSubwayResponseDto(Object[] objects) {
        return SubwayResponseDto.builder()
                .id((Integer) objects[0])
                .name(String.valueOf(objects[1]))
                .lat(String.valueOf(objects[2]))
                .lng(String.valueOf(objects[3]))
                .phoneNumber(String.valueOf(objects[4]))
                .dist(objects[5]+"m")
                .build();
    }
}
