package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
