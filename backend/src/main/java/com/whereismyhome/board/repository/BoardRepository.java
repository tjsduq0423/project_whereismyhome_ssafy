package com.whereismyhome.board.repository;

import com.whereismyhome.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(value = "select * from board where member_id = ?", nativeQuery = true)
    List<Board> findByMemberId(String id);

}
