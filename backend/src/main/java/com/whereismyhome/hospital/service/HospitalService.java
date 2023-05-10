package com.whereismyhome.hospital.service;

import com.whereismyhome.hospital.entity.Hospital;
import com.whereismyhome.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public List<Hospital> findAll(){
        List<Hospital> hospitalList = hospitalRepository.findAll();

        return hospitalList;
    }
}
