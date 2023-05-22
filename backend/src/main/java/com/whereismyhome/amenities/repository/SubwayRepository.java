package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
import com.whereismyhome.amenities.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {

    @Query(value = "select s.id, s.name, s.lat, s.lng,s.phone_number, cast(round(ST_Distance_Sphere(Point(:lng, :lat), s.local_point), 0) as signed) as dist " +
            "from subway s " +
            "where st_contains(st_buffer(Point(:lng, :lat), :dist), s.local_point) " +
            "order by ST_Distance_Sphere(Point(:lng, :lat), s.local_point);",nativeQuery = true)
    List<Object[]> findBySubway(@Param("lng") String lng, @Param("lat") String lat, @Param("dist") double dist);

    @Query(value = "select new com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto(s.name) " +
            "from Subway s " +
            "where s.name like %:word%")
    List<SubwaySearchResponseDto> findBySubwayName(@Param("word") String word);
}
