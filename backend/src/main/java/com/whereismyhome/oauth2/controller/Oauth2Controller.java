package com.whereismyhome.oauth2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whereismyhome.oauth2.service.Oauth2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
@Slf4j
public class Oauth2Controller {
    private final Oauth2Service oauth2Service;

    @GetMapping("/kakao")
    public ResponseEntity kakaoOauth(@RequestParam("code") String code) throws JsonProcessingException {
        log.info("code {}", code);
        String body = oauth2Service.kakaoLogin(code);
        ResponseEntity<String> kakaoUser = oauth2Service.kakaoUser(body);

        return ResponseEntity.ok().body(kakaoUser);
    }
}
