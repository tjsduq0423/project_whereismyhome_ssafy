package com.whereismyhome.shool.repository;

import com.whereismyhome.shool.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {
}
