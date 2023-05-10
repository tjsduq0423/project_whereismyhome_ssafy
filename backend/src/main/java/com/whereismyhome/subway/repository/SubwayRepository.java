package com.whereismyhome.subway.repository;

import com.whereismyhome.subway.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayRepository extends JpaRepository<Subway,Integer> {
}
