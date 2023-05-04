package com.whereismyhome.bookmark.service;

import com.whereismyhome.bookmark.dto.BookMarkServiceDto;
import com.whereismyhome.bookmark.entity.BookMark;
import com.whereismyhome.bookmark.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    //북마크 업데이트
    public void updateBookMark(BookMarkServiceDto serviceDto) {
        addBookMark(serviceDto.getAddList());
        deleteBookMark(serviceDto.getDeleteList());
    }

    //북마크 추가
    public void addBookMark(List<BookMark> addList) {
        if (addList.size() > 0) {
            for (BookMark bookMark : addList) {
                bookMarkRepository.save(bookMark);
            }
        }
    }

    //북마크 제거
    public void deleteBookMark(List<BookMark> deleteList) {
        if (deleteList.size() > 0) {
            for (BookMark bookMark : deleteList) {
                bookMarkRepository.deleteBookMark(bookMark.getMember().getId(), bookMark.getHouseInfo().getAptCode());
            }
        }
    }

    //북마크 조회 - 회원에 해당하는 북마크 리스트만
    public List<BookMark> getList(String id) {
        List<BookMark> bookMarkList = bookMarkRepository.findByMember_Id(id);

        return bookMarkList;
    }
}
