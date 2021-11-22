package com.ddang.auction.member;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.repository.MemberRepository;
import com.ddang.auction.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //구현체는 MemberJdbcTemplateRepository

    @Test
    void joinMember(){
        //given
        Member member = new Member();
//        member.setMemberId("test4");
        member.setPassword("testPW");
        member.setNickName("testNick");
        member.setEmail("test@email.com");
        member.setRegTime(LocalDateTime.now().toString());

        //when
//        Member savedMember = memberService.join(member);
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberService.findOne(savedMember.getId()).get();
//        assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
    }

    @Test
    void LoginMember(){
        //given
        LoginMember loginMember = new LoginMember();
//        loginMember.setMemberId("testID");
        loginMember.setPassword("testPassword");

        //when
//        Member member = memberService.login(loginMember);

        //then 
        //로그인 성공
//        assertThat(loginMember.getMemberId()).isEqualTo(member.getMemberId());
    }
}
