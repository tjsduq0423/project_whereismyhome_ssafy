package com.whereismyhome.oauth2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whereismyhome.oauth2.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class Oauth2Service {

    //카카오 리다이렉트 이후 토큰 요청 
    public String kakaoLogin(String code) {
        //추후 값 yml로 이동
        String client_id = "6c779f7199f5aa77f349e6165df482bc";
        String redirect_uri = "http://localhost:8080/oauth2/kakao";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", client_id);
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);
        body.add("grant_type","authorization_code");

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(body,headers);

        ResponseEntity<String> result = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        
        return result.getBody();
    }

    //카카오에서 받은 토큰으로 사용자 정보 요청
    public ResponseEntity<String> kakaoUser(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        TokenDto token = mapper.readValue(body, TokenDto.class);

        log.info("카카오 엑세스 토큰 {}",token.getAccess_token());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+token.getAccess_token());

        HttpEntity<MultiValueMap<String,String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        return result;
    }
}
