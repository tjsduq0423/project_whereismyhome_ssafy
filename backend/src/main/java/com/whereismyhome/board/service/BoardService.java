package com.whereismyhome.board.service;

import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.repository.BoardRepository;
import com.whereismyhome.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //게시글 조회
    public List<Board> boardList(String id) {
        List<Board> boardList = boardRepository.findByMemberId(id);

        return boardList;
    }
}
