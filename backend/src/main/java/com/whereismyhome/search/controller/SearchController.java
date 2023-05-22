package com.whereismyhome.search.controller;

import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
import com.whereismyhome.houseinfo.dto.HouseSearchResponseDto;
import com.whereismyhome.houseinfo.mapper.HouseInfoMapper;
import com.whereismyhome.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    private final HouseInfoMapper houseInfoMapper;
    //지하철 조회
    @GetMapping("/subway/{word}")
    public ResponseEntity findSubwayName(@PathVariable("word") String word) {
        List<SubwaySearchResponseDto> subwayName = searchService.findSubwayName(word);

        return ResponseEntity.ok().body(subwayName);
    }

    //아파트 이름 + 북마크 개수 조회
    @GetMapping("/house/{word}")
    public ResponseEntity findByHouseName(@PathVariable("word") String word) {
        List<HouseSearchResponseDto> houseSearchResponseDtos = houseInfoMapper.houseSearchDataToHouseSearchResponseDtos(searchService.findAptName(word));
        return ResponseEntity.ok().body(houseSearchResponseDtos);
    }
}
