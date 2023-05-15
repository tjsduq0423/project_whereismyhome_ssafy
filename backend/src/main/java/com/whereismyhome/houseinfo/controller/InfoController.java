package com.whereismyhome.houseinfo.controller;


import com.whereismyhome.houseinfo.dto.HouseInfoResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.mapper.HouseInfoMapper;
import com.whereismyhome.houseinfo.service.HouseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class InfoController {
    private final HouseInfoService houseInfoService;
    private final HouseInfoMapper mapper;

    //아파트 정보 조회 테스트
    @GetMapping("/{apt-code}")
    public ResponseEntity findHouse(@PathVariable("apt-code") long aptCode) {
        HouseInfo house = houseInfoService.findHouse(aptCode);
        HouseInfoResponseDto houseInfoResponseDto = mapper.infoToInfoResponseDto(house);

        return ResponseEntity.ok().body(houseInfoResponseDto);
    }
}
