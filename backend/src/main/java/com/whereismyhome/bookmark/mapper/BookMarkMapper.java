package com.whereismyhome.bookmark.mapper;

import com.whereismyhome.bookmark.dto.BookMarkDto;
import com.whereismyhome.bookmark.dto.BookMarkResponseDto;
import com.whereismyhome.bookmark.entity.BookMark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMarkMapper {

    List<BookMarkResponseDto> objectListToBookMarkResponseDtos(List<Object[]> objects);

    default BookMarkResponseDto objectToBookMarkResponseDto(Object[] objects) {
        return BookMarkResponseDto.builder()
                .aptCode((long) objects[0])
                .buildYear((int) objects[1])
                .areaName(String.valueOf(objects[2]))
                .max((long)objects[3])
                .min((long)objects[4])
                .aptName(String.valueOf(objects[5]))
                .lat(String.valueOf(objects[6]))
                .lng(String.valueOf(objects[7]))
                .build();
    }

    @Mapping(source = "houseInfo.aptCode",target = "aptCode")
    BookMarkResponseDto bookMarkToBookMarkResponseDto(BookMark bookMark);

    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "aptCode", target = "houseInfo.aptCode")
    BookMark bookMarkDtoToBookMark(BookMarkDto bookMarkDto);
}
