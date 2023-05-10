package com.whereismyhome.subway.service;

import com.whereismyhome.subway.entity.Subway;
import com.whereismyhome.subway.repository.SubwayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayService {
    private final SubwayRepository subwayRepository;

    public List<Subway> findAll(){
        List<Subway> subwayList = subwayRepository.findAll();

        return subwayList;
    }
}
