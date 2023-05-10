package com.whereismyhome.subway.controller;

import com.whereismyhome.subway.dto.SubwayResponseDto;
import com.whereismyhome.subway.mapper.SubwayMapper;
import com.whereismyhome.subway.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subway")
public class SubwayController {
    private final SubwayService subwayService;
    private final SubwayMapper mapper;

    @GetMapping("/list")
    public ResponseEntity getList(){
        List<SubwayResponseDto> subwayResponseDtos = mapper.subwayListToSubwayResponseDto(subwayService.findAll());

        return ResponseEntity.ok().body(subwayResponseDtos);
    }
}
