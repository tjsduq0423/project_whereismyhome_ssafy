package com.whereismyhome.hosedeal.service;

import com.whereismyhome.hosedeal.entity.HouseDeal;
import com.whereismyhome.hosedeal.repository.HouseDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseDealService {

    private final HouseDealRepository houseDealRepository;

    //아파트 매매 정보 조회
    public List<HouseDeal> findHouseDeal(long aptCode) {
        List<HouseDeal> dealList = houseDealRepository.findByHouseInfo_AptCode(aptCode);

        return dealList;
    }
}
