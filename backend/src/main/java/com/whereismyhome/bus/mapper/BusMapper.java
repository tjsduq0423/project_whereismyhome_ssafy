package com.whereismyhome.bus.mapper;

import com.whereismyhome.bus.dto.BusResponseDto;
import com.whereismyhome.bus.entity.Bus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {
    List<BusResponseDto> busListToBusResponseDtos(List<Bus> busList);
}
