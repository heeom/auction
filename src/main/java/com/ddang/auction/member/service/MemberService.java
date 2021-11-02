package com.ddang.auction.member.service;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

     private final MemberRepository memberRepository;

     public MemberService(MemberRepository memberRepository) {
          this.memberRepository = memberRepository;
     }

     /**
      * 회원가입
      * - 중복 회원 검증
      * - 회원 저장
      */
     public Member join(Member member){
          checkDuplicateMemberId(member);
          member.setRegTime(LocalDateTime.now().toString());
          return memberRepository.save(member);
     }

     private void checkDuplicateMemberId(Member member) {
          memberRepository.findByMemberId(member.getMemberId())
               .ifPresent(member1 -> {
                    throw new IllegalArgumentException("Duplicate MemberId");
               });
     }

     /**
      * 로그인 실패
      * @return null
      */
     public Member login(LoginMember loginMember) {
          return memberRepository.findByMemberId(loginMember.getMemberId())
                  .filter(m -> m.getPassword().equals(loginMember.getPassword()))
                  .orElse(null);
     }

     /**
      * 전체 회원 조회
      */
     public List<Member> findMembers(){
          return memberRepository.findAll();
     }

     /**
      * id로 회원 조회
      */
     public Optional<Member> findOne(Long id){
          return memberRepository.findById(id);
     }

     public boolean checkMemberIdExist(String memberId) {
         return memberRepository.findByMemberId(memberId).isPresent();
     }

     public boolean checkNickNameExist(String nickName){
          return memberRepository.findByNickName(nickName).isPresent();
     }

}
