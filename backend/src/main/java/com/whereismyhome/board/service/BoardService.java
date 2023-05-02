package com.whereismyhome.board.service;

import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.repository.BoardRepository;
import com.whereismyhome.jwt.JwtProvider;
import com.whereismyhome.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final JwtProvider jwtProvider;

    //게시판 글 등록
    public void regist(Board board) {
        boardRepository.save(board);
    }

    //게시글 삭제
    public void delete(int id) {
        boardRepository.deleteById(id);
    }
}
