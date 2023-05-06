package com.whereismyhome.bookmark.mapper;

import com.whereismyhome.bookmark.dto.BookMarkResponseDto;
import com.whereismyhome.bookmark.dto.BookMarkServiceDto;
import com.whereismyhome.bookmark.dto.BookMarkUpdateDto;
import com.whereismyhome.bookmark.entity.BookMark;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMarkMapper {

    default BookMarkServiceDto toBookMarkServiceDto(BookMarkUpdateDto updateDto) {
        BookMarkServiceDto markServiceDto = new BookMarkServiceDto();

        List<BookMark> add = new ArrayList<>();
        if(updateDto.getAddList() != null) {
            add = updateDto.getAddList().stream()
                    .map(bookMarkDto -> {
                        BookMark bookMark = new BookMark();
                        Member member = new Member();
                        HouseInfo houseInfo = new HouseInfo();
                        member.setId(updateDto.getId());
                        houseInfo.setAptCode(bookMarkDto.getAptCode());

                        bookMark.setMember(member);
                        bookMark.setHouseInfo(houseInfo);

                        return bookMark;
                    }).collect(Collectors.toList());
        }

        List<BookMark> delete = new ArrayList<>();
        if(updateDto.getDeleteList() != null) {
            delete = updateDto.getDeleteList().stream()
                    .map(bookMarkDto -> {
                        BookMark bookMark = new BookMark();
                        Member member = new Member();
                        HouseInfo houseInfo = new HouseInfo();
                        member.setId(updateDto.getId());
                        houseInfo.setAptCode(bookMarkDto.getAptCode());

                        bookMark.setMember(member);
                        bookMark.setHouseInfo(houseInfo);

                        return bookMark;
                    }).collect(Collectors.toList());
        }
        markServiceDto.setAddList(add);
        markServiceDto.setDeleteList(delete);
        return markServiceDto;
    }
    List<BookMarkResponseDto> bookMarkListToBookMarkResponseDtos(List<BookMark> bookMarkList);

    @Mapping(source = "houseInfo.aptCode",target = "aptCode")
    BookMarkResponseDto bookMarkToBookMarkResponseDto(BookMark bookMark);
}
