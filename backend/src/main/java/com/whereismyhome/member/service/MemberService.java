package com.whereismyhome.member.service;

import com.whereismyhome.exception.BusinessLogicException;
import com.whereismyhome.exception.ExceptionCode;
import com.whereismyhome.member.dto.MemberLoginDto;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.repository.MemberRepository;
import com.whereismyhome.role.entity.MemberRole;
import com.whereismyhome.role.repository.MemberRoleRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRoleRepository memberRoleRepository;
    private final JavaMailSender javaMailSender;

    //회원가입
    public Member join(Member member) throws IllegalAccessException {
        verifiedMember(member.getId());

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        String autho = "ROLE_USER";
        if(member.getId().contains("admin")){
            autho = "ROLE_ADMIN";
        }

        MemberRole role = new MemberRole();
        role.setRole(autho);
        role.setMember(member);
        member.setRoles(role);

        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

    //회원 id 중복 검사
    public void verifiedMember(String id){
        Optional<Member> memberId = memberRepository.findById(id);

        if(memberId.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_ID);
        }
    }

    //id로 회원 찾기
    public Member findUser(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    //비밀번호 확인
    public void checkPassWord(Member member, MemberLoginDto loginDto) {
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), member.getPassword());

        if(!matches){
            throw new BusinessLogicException(ExceptionCode.BAD_PARAM);
        }
    }

    //인증번호 생성
    public String createCode() {
        log.info("인증코드 생성 진입");
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        log.info("인증코드 생성 끝");
        return key.toString();
    }


    //메일 전송
    public String sendMail(String email) throws MessagingException {
        log.info("메일 전송 메서드 진입");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        
        //인증 코드 생성
        String key = createCode();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("whereismyhome 이메일 인증코드 입니다.");
        mimeMessageHelper.setText("인증코드는 : " + key + "입니다.");
        javaMailSender.send(mimeMessage);

        log.info("이메일 전송 성공");
        return key;
    }

    //비밀번호 변경
    public void changePassword(Member member) {
        Member findMember = findUser(member.getId());

        findMember.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(findMember);
    }

    //회원 중복 검사 api용 로직
    public boolean isCheck(String memeberId) {
        Optional<Member> findMember = memberRepository.findById(memeberId);

        return findMember.isEmpty();
    }

    //회원 삭제
    public void deleteMember(String memeberId) {
        memberRepository.deleteById(memeberId);
    }
}
