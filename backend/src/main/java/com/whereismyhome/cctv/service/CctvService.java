package com.whereismyhome.cctv.service;

import com.whereismyhome.cctv.entity.Cctv;
import com.whereismyhome.cctv.repository.CctvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CctvService {
    private final CctvRepository cctvRepository;

    public List<Cctv> findAll() {
        List<Cctv> cctvList = cctvRepository.findAll();
        return cctvList;
    }
}
