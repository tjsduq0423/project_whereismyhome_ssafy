package com.whereismyhome.notice.mapper;

import com.whereismyhome.notice.dto.NoticePostDto;
import com.whereismyhome.notice.dto.NoticeResponseDto;
import com.whereismyhome.notice.entity.Notice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    Notice noticePostDtoToNotice(NoticePostDto postDto);

    List<NoticeResponseDto> noticeListToNoticeResponseDto(List<Notice> noticeList);
}
