package com.whereismyhome.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createtime;
}
