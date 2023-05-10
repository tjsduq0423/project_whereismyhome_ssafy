package com.whereismyhome.shool.service;

import com.whereismyhome.shool.entity.School;
import com.whereismyhome.shool.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> findAll(){
        List<School> schoolList = schoolRepository.findAll();

        return schoolList;
    }

}
