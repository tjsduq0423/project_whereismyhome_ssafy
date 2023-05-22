package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Integer> {
    @Query(value = "select s.id, s.name, s.lat, s.lng , cast(round(ST_Distance_Sphere(Point(:lng, :lat), s.local_point), 0) as signed) as dist " +
            "from bus s " +
            "where st_contains(st_buffer(Point(:lng, :lat), :dist), s.local_point) " +
            "order by ST_Distance_Sphere(Point(:lng, :lat), s.local_point);",nativeQuery = true)
    List<Object[]> findByBus(@Param("lng") String lng, @Param("lat") String lat, @Param("dist") double dist);
}
