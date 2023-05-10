package com.whereismyhome.shool.controller;

import com.whereismyhome.shool.dto.SchoolResponseDto;
import com.whereismyhome.shool.mapper.SchoolMapper;
import com.whereismyhome.shool.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;
    private final SchoolMapper mapper;

    @GetMapping("/list")
    public ResponseEntity getList() {
        List<SchoolResponseDto> schoolResponseDtos = mapper.schoolListToSchoolResponseDtos(schoolService.findAll());

        return ResponseEntity.ok().body(schoolResponseDtos);
    }
}
