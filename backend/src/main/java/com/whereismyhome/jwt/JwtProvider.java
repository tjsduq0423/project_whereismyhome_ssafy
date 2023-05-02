package com.whereismyhome.jwt;


import com.whereismyhome.member.repository.MemberRepository;
import com.whereismyhome.member.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    //비밀키
    private String secretKey = "dasdasdasdasfadsdfsdfsfsfadfasdfadfaasdfasdfasdfasdfasdfasdfasdfasdfasdfasgebabadfsfsdfsdfadfabadbsdbsb";

    private final CustomUserDetailService customUserDetailService;
    private final MemberRepository memberRepository;

    private static Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return key;
    }


    //jwt토큰 생성
    public String createToken(String id, List<String> roles) {
        //토큰 제목
        Claims claims = Jwts.claims().setSubject(id);

        //유저 role 넣기 -> (관리자, 유저)
        claims.put("roles", roles);

        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    //엑세스토큰 생성
    public String createAccessToken(String id, List<String> roles) {
        return this.createToken(id, roles);
    }


    //토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public String getUserId(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(getSigningKey(secretKey)).build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }


    //유저 id 가져오기
    public String getMemberId(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey(secretKey)).build().parseClaimsJws(token).getBody().getSubject();
    }

    //권한 확인
    public List<String> getRoles(String id) {
        return memberRepository.findById(id).get().getRoles();
    }
}
