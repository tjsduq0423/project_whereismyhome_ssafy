package com.whereismyhome.bus.repository;

import com.whereismyhome.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {
}
