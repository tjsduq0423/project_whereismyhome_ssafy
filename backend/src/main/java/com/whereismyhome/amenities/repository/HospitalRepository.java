package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.HospitalResponseDto;
import com.whereismyhome.amenities.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.HospitalResponseDto(h.id, h.name,h.phoneNumber, h.lng, h.lat) from Hospital h where st_contains(st_buffer(Point(:lng,:lat),0.01),h.localPoint)order by ST_Distance_Sphere(Point(:lng,:lat),h.localPoint)")
    List<HospitalResponseDto> findByHospital(@Param("lng") String lng, @Param("lat") String lat);
}
