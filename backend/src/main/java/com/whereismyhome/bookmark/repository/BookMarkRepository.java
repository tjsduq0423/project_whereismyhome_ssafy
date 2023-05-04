package com.whereismyhome.bookmark.repository;

import com.whereismyhome.bookmark.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookMarkRepository extends JpaRepository<BookMark,Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from bookmark where member_id = ? and houseinfo_aptcode = ?", nativeQuery = true)
    void deleteBookMark(String id, long aptCode);
}
