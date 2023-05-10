package com.whereismyhome.shool.mapper;

import com.whereismyhome.shool.dto.SchoolResponseDto;
import com.whereismyhome.shool.entity.School;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    List<SchoolResponseDto> schoolListToSchoolResponseDtos(List<School> schoolList);
}
