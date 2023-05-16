package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.SchoolResponseDto;
import com.whereismyhome.amenities.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School,Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.SchoolResponseDto(s.id, s.name, s.lat, s.lng) from School s where st_contains(st_buffer(Point(:lng,:lat),0.01),s.localPoint)order by ST_Distance_Sphere(Point(:lng,:lat),s.localPoint)")
    List<SchoolResponseDto> findBySchool(@Param("lng") String lng, @Param("lat") String lat);
}
