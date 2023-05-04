package com.whereismyhome.bookmark.controller;

import com.whereismyhome.bookmark.dto.BookMarkUpdateDto;
import com.whereismyhome.bookmark.mapper.BookMarkMapper;
import com.whereismyhome.bookmark.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookMarkController {
    private final BookMarkService bookMarkService;
    private final BookMarkMapper mapper;

    //북마크 업데이트
    @PostMapping("/update")
    public ResponseEntity updateBookMark(@RequestBody BookMarkUpdateDto updateDto) {
        bookMarkService.updateBookMark(mapper.toBookMarkServiceDto(updateDto));

        return ResponseEntity.ok().body("북마크 업데이트");
    }
    //북마크 조회
}
