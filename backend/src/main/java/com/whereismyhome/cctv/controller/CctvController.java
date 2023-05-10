package com.whereismyhome.cctv.controller;

import com.whereismyhome.cctv.dto.CctvResponseDto;
import com.whereismyhome.cctv.mapper.CctvMapper;
import com.whereismyhome.cctv.service.CctvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cctv")
public class CctvController {
    private final CctvService cctvService;
    private final CctvMapper mapper;
    @GetMapping("/list")
    public ResponseEntity findAll(){
        List<CctvResponseDto> cctvResponseDtos = mapper.cctvListToCctvResponseDtos(cctvService.findAll());

        return ResponseEntity.ok().body(cctvResponseDtos);
    }
}
