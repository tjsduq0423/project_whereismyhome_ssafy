package com.whereismyhome.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private int id;
    private String memberId;
    private String title;
    private String content;
    private LocalDateTime createtime;
    private LocalDateTime modifiedtime;
    private String responseContent;

}
