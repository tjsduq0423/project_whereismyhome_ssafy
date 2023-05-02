package com.whereismyhome.board.mapper;

import com.whereismyhome.board.dto.BoardListResponseDto;
import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.entity.Board;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface BoardMapper {
    Board boardPostDtoToBoard (BoardPostDto postDto);

    @Mapping(source = "boardList", target = "boardListResponseDtos")
    List<BoardListResponseDto> boardListToBoardListResponseDto(List<Board> boardList);

}
