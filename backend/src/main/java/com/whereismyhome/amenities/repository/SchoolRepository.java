package com.whereismyhome.amenities.repository;

import com.whereismyhome.amenities.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {
}
