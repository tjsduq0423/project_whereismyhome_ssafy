package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query(value = "select h.id, h.name, h.phone_number, h.lng,h.lat, cast(round(ST_Distance_Sphere(Point(:lng, :lat), h.local_point), 0) as signed) as dist " +
            "from hospital h " +
            "where st_contains(st_buffer(Point(:lng, :lat), :dist), h.local_point) " +
            "order by ST_Distance_Sphere(Point(:lng, :lat), h.local_point);",nativeQuery = true)
    List<Object[]> findByHospital(@Param("lng") String lng, @Param("lat") String lat, @Param("dist") double dist);
}
