package com.ddang.auction.member;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.repository.MemberRepository;
import com.ddang.auction.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
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
    @Autowired MemberRepository memberRepository;


    @DisplayName("회원가입 테스트")
    @Test
    void joinMember(){
        //given
        Member member = new Member();
        member.setUsername("joinTestId");
        member.setPassword("testPW");
        member.setNickName("testNick");
        member.setEmail("test@email.com");
        member.setRegTime(LocalDateTime.now().toString());

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberService.findOne(savedMember.getId()).get();

        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
    
    @DisplayName("로그인 테스트")
    @Test
    void LoginMember(){
        //given
        LoginMember loginMember = new LoginMember();
        loginMember.setUsername("testID");
        loginMember.setPassword("testPassword");

        //when
//        Member member = memberService.login();

        //then 
        //로그인 성공
//        assertThat(loginMember.getMemberId()).isEqualTo(member.getMemberId());
    }

    @DisplayName("중복아이디 체크 테스트")
    @Test
    void checkUsernameExist() {
        Member member = new Member();
        member.setUsername("loginTest");

        Member findMember = memberRepository.findByUsername(member.getUsername()).get();

        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
    
    @DisplayName("중복 닉네임 체크 테스트")
    @Test
    void checkNickNameExist() {
        Member member = new Member();
        member.setNickName("security");

        Member findMember = memberRepository.findByNickName(member.getNickName()).get();

        assertThat(findMember.getNickName()).isEqualTo(member.getNickName());
    }

}
