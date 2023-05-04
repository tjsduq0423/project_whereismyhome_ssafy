package com.whereismyhome.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkResponseDto {
    List<BookMarkDto> bookMarkList;
}
