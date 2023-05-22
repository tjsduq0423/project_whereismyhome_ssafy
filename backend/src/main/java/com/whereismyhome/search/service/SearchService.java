package com.whereismyhome.search.service;

import com.whereismyhome.amenities.dto.response.SubwaySearchResponseDto;
import com.whereismyhome.amenities.repository.SubwayRepository;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SubwayRepository subwayRepository;
    private final HouseInfoRepository houseInfoRepository;

    //지하철 조회
    public List<SubwaySearchResponseDto> findSubwayName(String word) {
        return subwayRepository.findBySubwayName(word);
    }

    //아파트 이름 + 북마크 개수 조회
    public List<Object[]> findAptName(String word) {
        return houseInfoRepository.findByHouseName(word);
    }
}
