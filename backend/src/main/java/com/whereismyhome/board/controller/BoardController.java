package com.whereismyhome.board.controller;

import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.entity.Board;
import com.whereismyhome.board.mapper.BoardMapper;
import com.whereismyhome.board.service.BoardService;
import com.whereismyhome.jwt.JwtProvider;
import com.whereismyhome.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final JwtProvider jwtProvider;
    private final BoardMapper mapper;
    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody BoardPostDto postDto) {
        Board board = mapper.boardPostDtoToBoard(postDto);
        Member member = new Member();
        member.setId(postDto.getId());
        board.setMember(member);
        boardService.regist(board);
        return ResponseEntity.ok().build();
    }
}
