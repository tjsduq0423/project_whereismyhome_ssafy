package com.whereismyhome.bus.controller;

import com.whereismyhome.bus.dto.BusResponseDto;
import com.whereismyhome.bus.mapper.BusMapper;
import com.whereismyhome.bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;
    private final BusMapper mapper;

    @GetMapping("/list")
    public ResponseEntity busList(){
        List<BusResponseDto> busResponseDtos = mapper.busListToBusResponseDtos(busService.findAll());

        return ResponseEntity.ok().body(busResponseDtos);
    }
}
