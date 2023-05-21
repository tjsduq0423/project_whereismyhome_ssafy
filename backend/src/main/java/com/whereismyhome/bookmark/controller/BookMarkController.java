package com.whereismyhome.bookmark.controller;

import com.whereismyhome.bookmark.dto.BookMarkDto;
import com.whereismyhome.bookmark.dto.BookMarkResponseDto;
import com.whereismyhome.bookmark.entity.BookMark;
import com.whereismyhome.bookmark.mapper.BookMarkMapper;
import com.whereismyhome.bookmark.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookMarkController {
    private final BookMarkService bookMarkService;
    private final BookMarkMapper mapper;

    //북마크 등록
    @PostMapping("/add")
    public ResponseEntity addBookMark(@RequestBody BookMarkDto bookMarkDto) {
        BookMark bookMark = mapper.bookMarkDtoToBookMark(bookMarkDto);
        bookMarkService.addBookMark(bookMark);

        return ResponseEntity.status(HttpStatus.CREATED).body("북마크 정상 등록");
    }

    //북마크 제거
    @PostMapping("/delete")
    public ResponseEntity deleteBookMark(@RequestBody BookMarkDto bookMarkDto) {
        bookMarkService.deleteBookMark(bookMarkDto.getMemberId(), bookMarkDto.getAptCode());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("북마크 정상 삭제");
    }

    //북마크 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getList(@PathVariable("member-id") String memberId) {
        List<BookMarkResponseDto> bookMarkList = mapper.bookMarkListToBookMarkResponseDtos(bookMarkService.getList(memberId));
        return ResponseEntity.ok().body(bookMarkList);
    }
}
