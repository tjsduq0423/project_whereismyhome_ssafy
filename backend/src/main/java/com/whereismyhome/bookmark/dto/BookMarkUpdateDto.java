package com.whereismyhome.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkUpdateDto {
    private String id;
    private List<BookMarkDto> addList;
    private List<BookMarkDto> deleteList;
}
