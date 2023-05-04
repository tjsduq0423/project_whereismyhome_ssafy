package com.whereismyhome.notice.mapper;

import com.whereismyhome.notice.dto.NoticePostDto;
import com.whereismyhome.notice.dto.NoticeResponseDto;
import com.whereismyhome.notice.entity.Notice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    Notice noticePostDtoToNotice(NoticePostDto postDto);

    List<NoticeResponseDto> noticeListToNoticeResponseDto(List<Notice> noticeList);

    @Mapping(source = "modifedtime",target = "modifedtime")
    NoticeResponseDto noticeToNoticeResponseDto(Notice notice);
}
