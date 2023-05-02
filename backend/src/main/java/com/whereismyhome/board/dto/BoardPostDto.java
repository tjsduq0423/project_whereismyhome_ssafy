package com.whereismyhome.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostDto {
    private String memberId;
    private String title;
    private String content;
}
