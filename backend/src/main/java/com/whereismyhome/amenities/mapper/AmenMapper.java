package com.whereismyhome.amenities.mapper;

import com.whereismyhome.amenities.dto.response.BusResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AmenMapper {

    List<BusResponseDto> busDataListToBusResponseDtos(List<Object[]> objects);

    default BusResponseDto objectToBusResponseDto(Object[] objects){
        return BusResponseDto.builder()
                .id((int) objects[0])
                .name(String.valueOf(objects[1]))
                .lng(String.valueOf(objects[2]))
                .lat(String.valueOf(objects[3]))
                .dist((objects[4]) + "m")
                .build();
    }
}
