package com.whereismyhome.board.mapper;

import com.whereismyhome.board.dto.BoardAnswerDto;
import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.dto.BoardPutDto;
import com.whereismyhome.board.dto.BoardResponseDto;
import com.whereismyhome.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface BoardMapper {
    @Mapping(source = "memberId", target = "member.id")
    Board boardPostDtoToBoard (BoardPostDto postDto);

    Board boardPutDtoToBoard(BoardPutDto boardPutDto);

    @Mapping(source = "member.id", target = "memberId")
    BoardResponseDto boardToBoardResponseDto(Board board);

    List<BoardResponseDto> boardListToBoardListResponseDto(List<Board> boardList);

    Board boardAnswerDtoToBoard(BoardAnswerDto boardAnswerDto);

}
