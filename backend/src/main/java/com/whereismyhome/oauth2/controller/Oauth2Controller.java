package com.whereismyhome.oauth2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whereismyhome.jwt.JwtProvider;
import com.whereismyhome.member.dto.MemberResponseDto;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.mapper.MemberMapper;
import com.whereismyhome.oauth2.service.Oauth2Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@CrossOrigin( allowedHeaders = "*")
@RequestMapping("/oauth2")
@Slf4j
public class Oauth2Controller {
    private final Oauth2Service oauth2Service;
    private final MemberMapper mapper;
    private final JwtProvider jwtProvider;
    @PostMapping("/kakao")
    public ResponseEntity kakaoOauth(@RequestParam("code") String code, HttpServletResponse response) throws JsonProcessingException, IllegalAccessException {
        log.info("code {}", code);
        String body = oauth2Service.kakaoLogin(code);
        Member member = oauth2Service.kakaoUser(body);

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRoles().getRole());
        String encode = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);

        Cookie cookie = new Cookie("Authorization", encode);
        cookie.setPath(("/"));
        cookie.setHttpOnly(true);

        log.info("cookie : {}", cookie);
        response.addCookie(cookie);

        MemberResponseDto memberResponseDto = mapper.memberToMemberResponseDto(member);
        return ResponseEntity.ok().body(memberResponseDto);
    }
}
