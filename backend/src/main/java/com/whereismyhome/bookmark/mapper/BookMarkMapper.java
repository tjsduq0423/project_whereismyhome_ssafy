package com.whereismyhome.bookmark.mapper;

import com.whereismyhome.bookmark.dto.BookMarkDto;
import com.whereismyhome.bookmark.dto.BookMarkResponseDto;
import com.whereismyhome.bookmark.entity.BookMark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMarkMapper {

    List<BookMarkResponseDto> bookMarkListToBookMarkResponseDtos(List<BookMark> bookMarkList);

    @Mapping(source = "houseInfo.aptCode",target = "aptCode")
    BookMarkResponseDto bookMarkToBookMarkResponseDto(BookMark bookMark);

    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "aptCode", target = "houseInfo.aptCode")
    BookMark bookMarkDtoToBookMark(BookMarkDto bookMarkDto);
}
