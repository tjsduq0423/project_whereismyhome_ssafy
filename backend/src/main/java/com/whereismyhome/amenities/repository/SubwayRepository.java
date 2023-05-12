package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.SubwayResponseDto;
import com.whereismyhome.amenities.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.SubwayResponseDto(s.id, s.name, s.lat, s.lng,s.phoneNumber) from Subway s where st_contains(st_buffer(Point(:lng,:lat),0.01),s.localPoint)")
    List<SubwayResponseDto> findBySubway(@Param("lng") String lng, @Param("lat") String lat);
}
