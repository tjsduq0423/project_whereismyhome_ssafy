package com.whereismyhome.cctv.repository;

import com.whereismyhome.cctv.entity.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CctvRepository extends JpaRepository<Cctv,Integer> {
}
