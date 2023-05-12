package com.whereismyhome.houseinfo.repository;

import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    @Query("select new com.whereismyhome.houseinfo.dto.HouseResponseDto(h.aptCode,h.apartmentName,h.lng,h.lat) from HouseInfo h where st_contains(st_buffer(Point(:lng,:lat),0.01),h.localPoint)")
    List<HouseResponseDto> findByApt(@Param("lng") String lng, @Param("lat") String lat);
}
