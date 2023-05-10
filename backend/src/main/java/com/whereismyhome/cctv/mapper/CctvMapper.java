package com.whereismyhome.cctv.mapper;

import com.whereismyhome.cctv.dto.CctvResponseDto;
import com.whereismyhome.cctv.entity.Cctv;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CctvMapper {
    List<CctvResponseDto> cctvListToCctvResponseDtos(List<Cctv> cctvList);
}
