package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.SubwayResponseDto;
import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
import com.whereismyhome.amenities.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.SubwayResponseDto(s.id, s.name, s.lat, s.lng,s.phoneNumber) from Subway s where st_contains(st_buffer(Point(:lng,:lat),:dist),s.localPoint) order by ST_Distance_Sphere(Point(:lng,:lat),s.localPoint)")
    List<SubwayResponseDto> findBySubway(@Param("lng") String lng, @Param("lat") String lat,@Param("dist") double dist);

    @Query(value = "select new com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto(s.name) " +
            "from Subway s " +
            "where s.name like %:word%")
    List<SubwaySearchResponseDto> findBySubwayName(@Param("word") String word);
}
