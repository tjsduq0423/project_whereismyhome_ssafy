package com.whereismyhome.notice.controller;

import com.whereismyhome.notice.dto.NoticePostDto;
import com.whereismyhome.notice.dto.NoticeResponseDto;
import com.whereismyhome.notice.entity.Notice;
import com.whereismyhome.notice.mapper.NoticeMapper;
import com.whereismyhome.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    public ResponseEntity selectAll() {
        List<Notice> noticeList = noticeService.noticeList();
        List<NoticeResponseDto> responseDto = mapper.noticeListToNoticeResponseDto(noticeList);

        return ResponseEntity.ok().body(responseDto);
    }

    //공지사항 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity detailNotice(@PathVariable("id") int id) {
        NoticeResponseDto response = mapper.noticeToNoticeResponseDto(noticeService.detailNotice(id));

        return ResponseEntity.ok().body(response);
    }
}
