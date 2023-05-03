package com.whereismyhome.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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
    private LocalDateTime modifedtime;
}
