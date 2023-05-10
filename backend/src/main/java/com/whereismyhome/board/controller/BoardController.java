package com.whereismyhome.board.controller;

import com.whereismyhome.board.dto.BoardAnswerDto;
import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.dto.BoardPutDto;
import com.whereismyhome.board.dto.BoardResponseDto;
import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.mapper.BoardMapper;
import com.whereismyhome.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper mapper;

    //게시글 등록
    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody BoardPostDto postDto) {
        boardService.regist(mapper.boardPostDtoToBoard(postDto));
        return ResponseEntity.ok().build();
    }

    //게시글 삭제
    //게시글 번호
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }

    //게시글 목록 조회
    //자신이 쓴 게시글만 조회
    @GetMapping("/{id}")
    public ResponseEntity boardList(@PathVariable("id") String id) {
        List<Board> boards = boardService.boardList(id);
        List<BoardResponseDto> boardResponseDtos = mapper.boardListToBoardListResponseDto(boards);

        return ResponseEntity.ok().body(boardResponseDtos);
    }

    //게시글 수정
    @PutMapping("/update")
    public ResponseEntity boardUpdate(@RequestBody BoardPutDto boardPutDto) {
        Board board = boardService.boardUpdate(mapper.boardPutDtoToBoard(boardPutDto));
        BoardResponseDto boardResponseDto = mapper.boardToBoardResponseDto(board);

        return ResponseEntity.ok().body(boardResponseDto);
    }

    //게시글 답변 달기
    //관리자만 권한 있음
    @PostMapping("/answer")
    public ResponseEntity boardAnswer(@RequestBody BoardAnswerDto boardAnswerDto) {
        Board board = boardService.setAnswer(mapper.boardAnswerDtoToBoard(boardAnswerDto));
        BoardResponseDto boardResponseDto = mapper.boardToBoardResponseDto(board);

        return ResponseEntity.ok().body(boardResponseDto);
    }

    //게시글 상세 조회
    @GetMapping("{id}")
    public ResponseEntity detailBoard(@PathVariable("id") int id) {
        BoardResponseDto boardResponseDto = mapper.boardToBoardResponseDto(boardService.boardDetail(id));

        return ResponseEntity.ok().body(boardResponseDto);
    }

    //게시글 조회 - 답변이 달리지 않은 것만 조회
    //관리자용
    @GetMapping("/list")
    public ResponseEntity boardList() {
        List<Board> boardList = boardService.adminBoardList();
        List<BoardResponseDto> boardResponseDtos = mapper.boardListToBoardListResponseDto(boardList);

        return ResponseEntity.ok().body(boardResponseDtos);
    }
}
