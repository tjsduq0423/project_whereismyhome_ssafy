package com.whereismyhome.board.repository;

import com.whereismyhome.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findByMember_Id(String id);

    List<Board> findByResponseContentNull();

}
