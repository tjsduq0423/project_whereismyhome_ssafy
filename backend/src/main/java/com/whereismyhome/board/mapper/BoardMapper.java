package com.whereismyhome.board.mapper;

import com.whereismyhome.board.dto.BoardPostDto;
import com.whereismyhome.board.entity.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board boardPostDtoToBoard (BoardPostDto postDto);
}
