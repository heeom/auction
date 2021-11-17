package com.ddang.auction.member.service;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.Role;
import com.ddang.auction.member.domain.RoleConst;
import com.ddang.auction.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

     private final MemberRepository memberRepository;
     private final PasswordEncoder passwordEncoder;

     public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
          this.memberRepository = memberRepository;
          this.passwordEncoder = passwordEncoder;
     }

     /**
      * 회원가입
      * - 중복 회원 검증
      * - 회원 저장
      */
     public Member join(Member member){
          checkDuplicateUsername(member);
          member.setRegTime(LocalDateTime.now().toString());

          String encodedPassword = passwordEncoder.encode(member.getPassword());
          member.setPassword(encodedPassword);

          member.setEnabled(true);

          Role role = new Role();
          role.setRoleId(RoleConst.ROLE_USER);
          member.getRoles().add(role);

          return memberRepository.save(member);
     }

     private void checkDuplicateUsername(Member member) {
          memberRepository.findByUsername(member.getUsername())
               .ifPresent(member1 -> {
                    throw new IllegalArgumentException("Duplicate MemberId");
               });
     }

     /**
      * 로그인 실패
      * @return null
      */
     public Member login(LoginMember loginMember) {
          return memberRepository.findByUsername(loginMember.getUsername())
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

     public boolean checkUsernameExist(String username) {
         return memberRepository.findByUsername(username).isPresent();
     }

     public boolean checkNickNameExist(String nickName){
          return memberRepository.findByNickName(nickName).isPresent();
     }

}
