package com.whereismyhome.dongcode.service;

import com.whereismyhome.dongcode.repository.DongCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DongCodeService {
    private final DongCodeRepository dongCodeRepository;

    //지역구별 평균 매매가
    public List<Object[]> findGuGunAvgMount() {
        return dongCodeRepository.findByGuGunAvgMount();
    }
}
