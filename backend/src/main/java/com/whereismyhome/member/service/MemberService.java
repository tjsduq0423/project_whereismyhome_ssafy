package com.whereismyhome.member.service;

import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import com.whereismyhome.member.dto.MemberJoinDto;
import com.whereismyhome.member.dto.MemberLoginDto;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.repository.MemberRepository;
import com.whereismyhome.role.entity.MemberRole;
import com.whereismyhome.role.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRoleRepository memberRoleRepository;

    //회원가입
    public void join(MemberJoinDto postDto) throws IllegalAccessException {
        verifiedMember(postDto.getId());

        Member member = Member.builder()
                .id(postDto.getId())
                .password(passwordEncoder.encode(postDto.getPassword()))
                .email(postDto.getEmail())
                .name(postDto.getName())
//                .roles(roles)
                .build();

        String autho = "ROLE_USER";
        if(postDto.getId().contains("admin")){
            autho = "ROLE_ADMIN";
        }

        MemberRole role = new MemberRole();
        role.setRole(autho);
        role.setMember(member);

//        member.getRoles().add(role);
        memberRepository.save(member);
        memberRoleRepository.save(role);
    }

    //회원 id 중복 검사
    public void verifiedMember(String id){
        Optional<Member> memberId = memberRepository.findById(id);

        if(memberId.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_ID);
        }
    }

    public Member findUser(MemberLoginDto loginDto) {
        Member member = memberRepository.findById(loginDto.getId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BAD_PARAM));
        return member;
    }

    public void checkPassWord(Member member, MemberLoginDto loginDto) {
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), member.getPassword());

        if(!matches){
            throw new BusinessLogicException(ExceptionCode.BAD_PARAM);
        }
    }
}
