package com.whereismyhome.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardPutDto {
    private int id;
    private String title;
    private String content;
}
