package com.whereismyhome.notice.service;

import com.whereismyhome.notice.entity.Notice;
import com.whereismyhome.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    //공지사항 작성
    public void postNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    //공지사항 전체 조회
}
