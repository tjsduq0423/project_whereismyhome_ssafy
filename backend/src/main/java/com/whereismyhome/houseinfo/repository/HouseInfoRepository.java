package com.whereismyhome.houseinfo.repository;

import com.whereismyhome.houseinfo.dto.HousePointDto;
import com.whereismyhome.houseinfo.dto.HouseResponseDto;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    @Query("select new com.whereismyhome.houseinfo.dto.HouseResponseDto(h.aptCode,h.apartmentName,h.lng,h.lat) from HouseInfo h where st_contains(st_buffer(Point(:lng,:lat),0.01),h.localPoint)")
    List<HouseResponseDto> findByApt(@Param("lng") String lng, @Param("lat") String lat);

    @Query("select new com.whereismyhome.houseinfo.dto.HousePointDto(h.lng, h.lat) from HouseInfo h where h.aptCode = :aptCode")
    HousePointDto findByPoint(@Param("aptCode") long aptCode);

    //북마크, 조회수, 거래량 조회 쿼리 사용
    @Query(value = "SELECT COUNT(*) AS amount, h.apt_code as aptCode, hi.viewcount, coalesce(b.bookmark_count, 0) as mark," +
            "       (count(*) + hi.viewcount + coalesce(b.bookmark_count, 0)) as total " +
            "FROM housedeal h " +
            "         JOIN houseinfo hi ON h.apt_code = hi.apt_code " +
            "         JOIN dongcode d ON d.dong_code = hi.dong_code " +
            "         LEFT JOIN ( " +
            "            SELECT houseinfo_aptcode, COUNT(*) AS bookmark_count " +
            "            FROM bookmark " +
            "            GROUP BY houseinfo_aptcode " +
            ") b ON b.houseinfo_aptcode = h.apt_code " +
            "WHERE d.sido_name = :sidoName AND d.gugun_name = :gugunName " +
            "GROUP BY h.apt_code, hi.viewcount, hi.dong, b.bookmark_count " +
            "order by total desc;"
            ,nativeQuery = true)
    List<Object[]> makeRank(@Param("sidoName") String sidoName, @Param("gugunName") String gugunName);

}
