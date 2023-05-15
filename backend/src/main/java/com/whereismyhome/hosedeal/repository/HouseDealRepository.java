package com.whereismyhome.hosedeal.repository;

import com.whereismyhome.hosedeal.entity.HouseDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseDealRepository extends JpaRepository<HouseDeal,Long> {

    List<HouseDeal> findByHouseInfo_AptCode(long aptCode);
}
