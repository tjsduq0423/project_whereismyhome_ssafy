package com.whereismyhome.search.service;

import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
import com.whereismyhome.amenities.repository.SubwayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SubwayRepository subwayRepository;

    //지하철 조회
    public List<SubwaySearchResponseDto> findSubwayName(String word) {
        return subwayRepository.findBySubwayName(word);
    }
}
