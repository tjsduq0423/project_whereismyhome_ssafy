package com.whereismyhome.member.mapper;

import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberJoinDtoToMember(MemberJoinDto memberJoinDto);
}
