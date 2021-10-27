package com.ddang.auction.member;

import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.repository.MemberRepository;
import com.ddang.auction.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //구현체는 MemberJdbcTemplateRepository

    @Test
    void joinMember(){
        //given
        Member member = new Member();
        member.setMemberId("testID");
        member.setPassword("testPW");
        member.setNickName("testNick");
        member.setEmail("test@email.com");
        member.setRegTime(LocalDateTime.now().toString());

        //when
        Member savedMember = memberService.join(member);

        //then
        Member findMember = memberService.findOne(savedMember.getId()).get();
        Assertions.assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
    }

    @Test
    void LoginMember(){
        //given
        String loginId = "testID";
        String password = "testPassword";

        //when
        Member loginMember = memberService.LoginMember(loginId, password);

        //then 
        //로그인 성공
        Assertions.assertThat(loginMember.getMemberId()).isEqualTo(loginId);
    }
}
