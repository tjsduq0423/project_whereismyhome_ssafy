package com.whereismyhome.member.service;

import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public void join(Member member) throws IllegalAccessException {
        verifiedMember(member.getId());

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    //회원 id 중복 검사
    //회원찾기
    public void verifiedMember(String id) throws IllegalAccessException {
        Optional<Member> memberId = memberRepository.findById(id);

        if(memberId.isPresent()) {
            throw new IllegalAccessException("중복된 아이디입니다.");
        }
    }
}
