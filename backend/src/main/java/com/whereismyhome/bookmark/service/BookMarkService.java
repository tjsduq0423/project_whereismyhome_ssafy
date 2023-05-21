package com.whereismyhome.bookmark.service;

import com.whereismyhome.bookmark.entity.BookMark;
import com.whereismyhome.bookmark.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    //북마크 추가
    public void addBookMark(BookMark bookMark) {

        bookMarkRepository.save(bookMark);
    }

    //북마크 제거
    public void deleteBookMark(String memberId, long aptCode) {
        bookMarkRepository.deleteBookMark(memberId, aptCode);
    }

    //북마크 조회 - 회원에 해당하는 북마크 리스트만
    public List<BookMark> getList(String id) {
        List<BookMark> bookMarkList = bookMarkRepository.findByMember_Id(id);

        return bookMarkList;
    }
}
