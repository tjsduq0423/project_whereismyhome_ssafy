package com.whereismyhome.dongcode.repository;

import com.whereismyhome.dongcode.entity.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DongCodeRepository extends JpaRepository<DongCode, String> {

    @Query(value = "select gugun_name,cast(round(avg(h.avg), -3) as signed ) as avg " +
            "from dongcode " +
            "join houseinfo h on dongcode.dong_code = h.dong_code " +
            "group by gugun_name;", nativeQuery = true)
    List<Object[]> findByGuGunAvgMount();
}
