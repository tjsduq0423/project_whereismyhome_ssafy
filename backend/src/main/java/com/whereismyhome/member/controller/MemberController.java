package com.whereismyhome.member.controller;


import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.mapper.MemberMapper;
import com.whereismyhome.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //회원 가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.join(memberJoinDto);
        return ResponseEntity.ok().build();
    }


    //로그인

    //로그아웃
}
