package com.whereismyhome.oauth2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class Oauth2Service {
    private final MemberService memberService;

    //카카오 리다이렉트 이후 토큰 요청 
    public String kakaoLogin(String code) {
        //추후 값 yml로 이동
        String client_id = "6c779f7199f5aa77f349e6165df482bc";
        String redirect_uri = "http://localhost:8080/oauth2/kakao";

        WebClient webClient = WebClient.create();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", client_id);
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);
        body.add("grant_type","authorization_code");

        String response = webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(body))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    //카카오에서 받은 토큰으로 사용자 정보 요청
    public Member kakaoUser(String body) throws JsonProcessingException, IllegalAccessException {
        ObjectMapper mapper = new ObjectMapper();

        String token = mapper.readTree(body).get("access_token").asText();

        log.info("카카오 엑세스 토큰 {}",token);

        WebClient webClient = WebClient.create();

        log.info("카카오 사용자 정보 요청");
        String response = webClient.post()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("사용자 정보 받아옴");

        JsonNode jsonNode = mapper.readTree(response);
        String name = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();
        log.info("name : {}", name);
        log.info("email : {}", email);

        Member member = loginAndRegist(name, email);

        return member;
    }

    //회원가입 및 로그인
    public Member loginAndRegist(String name, String email) throws IllegalAccessException {
        Member kakaoMember = Member.builder()
                .id(email)
                .email(email)
                .name(name)
                .password("ssafy")
                .build();
        try {
            memberService.findUser(kakaoMember.getId());
        } catch (BusinessLogicException e) {
            memberService.join(kakaoMember);
        }

        return kakaoMember;

    }
}
