package com.whereismyhome.board.service;

import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.repository.BoardRepository;
import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

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
        List<Board> boardList = boardRepository.findByMember_Id(id);

        return boardList;
    }

    //게시글 수정
    public Board boardUpdate(Board board) {
        Board findBoard = findBoard(board.getId());

        Optional.ofNullable(findBoard.getTitle())
                .ifPresent(findBoard::setTitle);
        Optional.ofNullable(findBoard.getContent())
                .ifPresent(findBoard::setContent);

        findBoard.setModifiedtime(LocalDateTime.now());

        return boardRepository.save(findBoard);
    }

    //게시글 조회 - 관리자용
    //답변이 안달린 모든 게시글
    public List<Board> adminBoardList(){
        List<Board> boardList = boardRepository.findByResponseContentNull();

        return boardList;
    }


    //게시글에 답변 달기
    public Board setAnswer(Board board) {
        Board findBoard = findBoard(board.getId());

        findBoard.setResponseContent(board.getResponseContent());

        return boardRepository.save(findBoard);
    }

    //게시글 유효성 검사
    public Board findBoard(int id) {
        Optional<Board> findBoard = boardRepository.findById(id);

        return findBoard.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
