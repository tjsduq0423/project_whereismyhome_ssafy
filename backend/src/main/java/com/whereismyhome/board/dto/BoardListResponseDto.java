package com.whereismyhome.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {
    private int id;
    private String memberId;
    private String title;
    private String content;
    private LocalDateTime createtime;
    private String responseContent;

}
