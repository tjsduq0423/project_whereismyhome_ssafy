package com.whereismyhome.hospital.mapper;

import com.whereismyhome.hospital.dto.HospitalResponseDto;
import com.whereismyhome.hospital.entity.Hospital;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {
    List<HospitalResponseDto> hospitalListToHospitalResponseDtos(List<Hospital> hospitalList);
}
