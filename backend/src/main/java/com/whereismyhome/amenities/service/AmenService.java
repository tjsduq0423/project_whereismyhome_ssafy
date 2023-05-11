package com.whereismyhome.amenities.service;

import com.whereismyhome.amenities.entity.*;
import com.whereismyhome.amenities.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenService {
    private final BusRepository busRepository;
    private final HospitalRepository hospitalRepository;
    private final SchoolRepository schoolRepository;
    private final SubwayRepository subwayRepository;
    private final CctvRepository cctvRepository;

    //버스 조회
    public List<Bus> findBus(){
        List<Bus> busList = busRepository.findAll();

        return busList;
    }
    //cctv 조회
    public List<Cctv> findCctv() {
        List<Cctv> cctvList = cctvRepository.findAll();
        return cctvList;
    }
    //학교 조회
    public List<School> findSchool(){
        List<School> schoolList = schoolRepository.findAll();

        return schoolList;
    }
    //지하철 조회
    public List<Subway> findSubway(){
        List<Subway> subwayList = subwayRepository.findAll();

        return subwayList;
    }
    //병원 조회
    public List<Hospital> findHospital(){
        List<Hospital> hospitalList = hospitalRepository.findAll();

        return hospitalList;
    }
}
