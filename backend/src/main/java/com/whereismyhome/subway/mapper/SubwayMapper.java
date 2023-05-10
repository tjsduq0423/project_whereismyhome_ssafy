package com.whereismyhome.subway.mapper;

import com.whereismyhome.subway.dto.SubwayResponseDto;
import com.whereismyhome.subway.entity.Subway;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubwayMapper {
    List<SubwayResponseDto> subwayListToSubwayResponseDto(List<Subway> subwayList);

}
