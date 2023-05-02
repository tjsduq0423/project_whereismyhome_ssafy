package com.whereismyhome.board.controller;

import com.whereismyhome.board.dto.BoardListResponseDto;
import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.mapper.BoardMapper;
import com.whereismyhome.board.service.BoardService;
import com.whereismyhome.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        Board board = mapper.boardPostDtoToBoard(postDto);
        Member member = new Member();
        member.setId(postDto.getMemberId());
        board.setMember(member);
        boardService.regist(board);
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
        List<BoardListResponseDto> boardListResponseDtos = mapper.boardListToBoardListResponseDto(boards);

        return new ResponseEntity(boardListResponseDtos, HttpStatus.OK);
    }
}
