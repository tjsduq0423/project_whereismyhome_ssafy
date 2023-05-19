package com.whereismyhome.member.mapper;

import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.dto.MemberLoginDto;
import com.whereismyhome.member.dto.MemberResponseDto;
import com.whereismyhome.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberResponseDto memberToMemberResponseDto(Member member);

    Member memberJoinDtoToMember(MemberJoinDto memberJoinDto);

    Member memberLoginDtoToMember(MemberLoginDto loginDto);
}
