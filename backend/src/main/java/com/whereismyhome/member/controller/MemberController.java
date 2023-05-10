package com.whereismyhome.member.controller;


import com.whereismyhome.jwt.JwtProvider;
import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.dto.MemberLoginDto;
import com.whereismyhome.member.dto.MemberResponseDto;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.mapper.MemberMapper;
import com.whereismyhome.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberMapper mapper;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    //회원 가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.join(memberJoinDto);
        return ResponseEntity.ok().body("회원가입을 축하합니다.");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberLoginDto loginDto, HttpServletResponse response) {
        Member member = memberService.findUser(loginDto);
        memberService.checkPassWord(member, loginDto);

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRoles().getRole());
        log.info("토큰 정상 생성");
//        String encode = URLEncoder.encode("Bearer " + accessToken, StandardCharsets.UTF_8);
        String encode = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);

        Cookie cookie = new Cookie("Authorization", encode);
        cookie.setPath(("/"));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        MemberResponseDto memberResponseDto = mapper.memberToMemberResponseDto(member);

        return ResponseEntity.ok().body(memberResponseDto);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }
}
