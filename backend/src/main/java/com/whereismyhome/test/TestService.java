package com.whereismyhome.test;

import com.whereismyhome.bookmark.entity.BookMark;
import com.whereismyhome.bookmark.repository.BookMarkRepository;
import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.houseinfo.repository.HouseInfoRepository;
import com.whereismyhome.member.entity.Member;
import com.whereismyhome.member.repository.MemberRepository;
import com.whereismyhome.member.service.MemberService;
import com.whereismyhome.role.entity.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final BookMarkRepository bookMarkRepository;
    private final HouseInfoRepository houseInfoRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void testMember(){
        for (int i = 1001; i <= 10000; i++) {
            Member member = Member.builder()
                    .id("ssafy" + i)
                    .password(passwordEncoder.encode("ssafy"))
                    .name("ssafy" + i)
                    .email("ssafy" + i + "@gmail.com")
                    .build();

            MemberRole role = new MemberRole();
            role.setRole("ROLE_USER");
            role.setMember(member);
            member.setRoles(role);

            memberRepository.save(member);
        }
    }

    //북마크 삽입
    public void test() {

        List<HouseInfo> houseInfoList = houseInfoRepository.findAll();

        for (int i = 140; i <= 10000; i++) {
            if (i == 1001) continue;
            Member member = new Member();
            member.setId("ssafy"+i);

            for (int j = 0; j < 80; j++) {
                Random random = new Random();

                int idx = random.nextInt(42233);

                HouseInfo houseInfo = houseInfoList.get(idx);

                BookMark bookMark = new BookMark();
                bookMark.setMember(member);
                bookMark.setHouseInfo(houseInfo);

//                log.info("member : {}", member.getId());
//                log.info("아파트코드 : {}",houseInfo.getAptCode());

                bookMarkRepository.save(bookMark);
            }
        }
    }
}
