package com.whereismyhome.notice.controller;

import com.whereismyhome.notice.dto.NoticePostDto;
import com.whereismyhome.notice.mapper.NoticeMapper;
import com.whereismyhome.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeMapper mapper;
    private final NoticeService noticeService;

    //공지사항 등록
    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody NoticePostDto postDto) {
        noticeService.postNotice(mapper.noticePostDtoToNotice(postDto));

        return ResponseEntity.ok().build();
    }


    //공지사항 전체 조회

    //공지사항 하나 조회
}
