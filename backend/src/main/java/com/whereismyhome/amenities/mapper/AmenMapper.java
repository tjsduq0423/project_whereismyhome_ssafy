package com.whereismyhome.amenities.mapper;

import com.whereismyhome.amenities.dto.*;
import com.whereismyhome.amenities.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AmenMapper {
    List<BusResponseDto> busListToBusResponseDtos(List<Bus> busList);

    List<CctvResponseDto> cctvListToCctvResponseDtos(List<Cctv> cctvList);

    List<HospitalResponseDto> hospitalListToHospitalResponseDtos(List<Hospital> hospitalList);

    List<SchoolResponseDto> schoolListToSchoolResponseDtos(List<School> schoolList);

    List<SubwayResponseDto> subwayListToSubwayResponseDto(List<Subway> subwayList);
}

