package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.dto.response.BusResponseDto;
import com.whereismyhome.amenities.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Integer> {

    @Query("select new com.whereismyhome.amenities.dto.response.BusResponseDto(b.id, b.name, b.lat, b.lng) from Bus b where st_contains(st_buffer(Point(:lng,:lat),0.01),b.localPoint)")
    List<BusResponseDto> findByBus(@Param("lng") String lng, @Param("lat") String lat);
}
