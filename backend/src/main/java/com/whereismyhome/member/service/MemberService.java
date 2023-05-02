package com.whereismyhome.member.service;

import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.dto.MemberLoginDto;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public void join(MemberJoinDto postDto) throws IllegalAccessException {
        verifiedMember(postDto.getId());

        Member member = Member.builder()
                .id(postDto.getId())
                .password(passwordEncoder.encode(postDto.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        memberRepository.save(member);
    }

    //회원 id 중복 검사
    public void verifiedMember(String id) throws IllegalAccessException {
        Optional<Member> memberId = memberRepository.findById(id);

        if(memberId.isPresent()) {
            throw new IllegalAccessException("중복된 아이디입니다.");
        }
    }

    public Member findUser(MemberLoginDto loginDto) {
        Member member = memberRepository.findById(loginDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호가 잘 못 되었음 "));
        return member;
    }

    public boolean checkPassWord(Member member, MemberLoginDto loginDto) {
        return passwordEncoder.matches(loginDto.getPassword(), member.getPassword());
    }
}
