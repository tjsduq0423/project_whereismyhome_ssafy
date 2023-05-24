package com.whereismyhome.bookmark.repository;

import com.whereismyhome.bookmark.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from bookmark where member_id = ? and houseinfo_aptcode = ?", nativeQuery = true)
    void deleteBookMark(String id, long aptCode);

    List<BookMark> findByMember_Id(String id);

    @Query(value = "select h.apt_code, " +
            "       h.build_year, " +
            "       concat(d.sido_name, ' ', d.dong_name) as sigunguName, " +
            "       max(cast(replace(hd.deal_amount, ',', '') as signed)) as max, " +
            "       min(cast(replace(hd.deal_amount, ',', '') as signed)) as min, " +
            "       h.apartment_name, " +
            "       h.lat, " +
            "       h.lng " +
            "from housedeal hd " +
            "         join houseinfo h on h.apt_code = hd.apt_code " +
            "         join dongcode d on d.dong_code = h.dong_code " +
            "where h.apt_code in (select houseinfo_aptcode " +
            "                     from bookmark " +
            "                     where member_id = :memberId) " +
            "group by hd.apt_code;", nativeQuery = true)
    List<Object[]> findByMyPageBookMark(@Param("memberId") String memberId);
}
