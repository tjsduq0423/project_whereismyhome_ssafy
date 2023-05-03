package com.whereismyhome.notice.service;

import com.whereismyhome.notice.dto.NoticePutDto;
import com.whereismyhome.notice.entity.Notice;
import com.whereismyhome.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    //공지사항 작성
    public void postNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    //공지사항 전체 조회
    public List<Notice> noticeList() {
        List<Notice> list = noticeRepository.findAll();
        return list;
    }

    //공지사항 찾기 및 유효성 검사
    public Notice findNotice(int id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    //공지사항 상세 조회
    public Notice detailNotice(int id) {
        return findNotice(id);
    }

    //공지사항 수정
    public void updateNotice(NoticePutDto putDto) {
        Notice findNotice = findNotice(putDto.getId());

        Optional.ofNullable(putDto.getTitle())
                .ifPresent(findNotice::setTitle);
        Optional.ofNullable(putDto.getContent())
                .ifPresent(findNotice::setContent);

        findNotice.setModifedtime(LocalDateTime.now());

        noticeRepository.save(findNotice);
    }

}
