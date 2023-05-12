package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.CctvResponseDto;
import com.whereismyhome.amenities.entity.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CctvRepository extends JpaRepository<Cctv,Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.CctvResponseDto(c.id, c.usage, c.lat, c.lng) from Cctv c where st_distance_sphere(Point(c.lng,c.lat),Point(:lng,:lat)) <= 1000")
    List<CctvResponseDto> findByCctv(@Param("lng") String lng, @Param("lat") String lat);
}
