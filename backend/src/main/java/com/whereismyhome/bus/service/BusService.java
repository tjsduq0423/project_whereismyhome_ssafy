package com.whereismyhome.bus.service;

import com.whereismyhome.bus.entity.Bus;
import com.whereismyhome.bus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    //조회
    public List<Bus> findAll(){
        List<Bus> busList = busRepository.findAll();

        return busList;
    }
}
