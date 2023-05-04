package com.whereismyhome.bookmark.dto;

import com.whereismyhome.bookmark.entity.BookMark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkServiceDto {
    private List<BookMark> addList;
    private List<BookMark> deleteList;
}
