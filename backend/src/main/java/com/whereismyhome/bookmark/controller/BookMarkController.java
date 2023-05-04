package com.whereismyhome.bookmark.controller;

import com.whereismyhome.bookmark.dto.BookMarkResponseDto;
import com.whereismyhome.bookmark.dto.BookMarkUpdateDto;
import com.whereismyhome.bookmark.mapper.BookMarkMapper;
import com.whereismyhome.bookmark.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{member-id}")
    public ResponseEntity getList(@PathVariable("member-id") String memberId) {
        List<BookMarkResponseDto> bookMarkList = mapper.bookMarkListToBookMarkResponseDtos(bookMarkService.getList(memberId));
        return ResponseEntity.ok().body(bookMarkList);
    }
}
