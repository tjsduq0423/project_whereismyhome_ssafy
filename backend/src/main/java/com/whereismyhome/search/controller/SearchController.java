package com.whereismyhome.search.controller;

import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
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

    //지하철 조회
    @GetMapping("/subway/{word}")
    public ResponseEntity findSubwayName(@PathVariable("word") String word) {
        List<SubwaySearchResponseDto> subwayName = searchService.findSubwayName(word);

        return ResponseEntity.ok().body(subwayName);
    }
}
