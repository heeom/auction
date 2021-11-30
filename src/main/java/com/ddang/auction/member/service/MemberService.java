package com.ddang.auction.member.service;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.Role;
import com.ddang.auction.member.domain.RoleConst;
import com.ddang.auction.member.repository.MemberRepository;
import com.ddang.auction.web.security.SecurityUtil;
import com.ddang.auction.web.security.dto.TokenDto;
import com.ddang.auction.web.security.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

     private final MemberRepository memberRepository;
     private final PasswordEncoder passwordEncoder;
     private final AuthenticationManagerBuilder authenticationManagerBuilder;
     private final TokenProvider tokenProvider;
     /**
      * 회원가입
      * - 중복 회원 검증
      * - 회원 저장
      */
     @Transactional
     public Member join(Member member){
          checkDuplicateUsername(member);

          member.setRegTime(LocalDateTime.now().toString());
          member.setPassword(passwordEncoder.encode(member.getPassword()));
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

     public TokenDto login(LoginMember loginMember) {
          //1. id/pw 기반으로 AuthenticationToken생성
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginMember.getUsername(), loginMember.getPassword());
          //2. id/pw검증 : authentication 메서드가 실행될 때, CustomUserDetailsService.loadUserByUsername 실행
          //AuthenticationManager : 실제 인증과정이 수행됨 
          //                       - authenticate(authentication) : UserDetails의 유저정보와 authenticationToken의 유저 정보가 일치하는지 검사한다.
          //                       - 인증이 완료된 Authentication 객체에는 username(id)가 들어있다.
          Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

          //3. 인증정보를 기반으로 JWT 토큰 생성
          TokenDto token = tokenProvider.createToken(authentication);
          
          //4. 생성된 토큰을 클라이언트에 전달
          return token;
     }
     
     //SecurityContext에 저장되어 있는 유저 정보 가져오기
//     public Member getUserInfo(){
//          return memberRepository.findById(SecurityUtil.getCurrentUsername())
//     }


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

     @Transactional(readOnly = true)
     public boolean checkUsernameExist(String username) {
         return memberRepository.findByUsername(username).isPresent();
     }

     @Transactional(readOnly = true)
     public boolean checkNickNameExist(String nickName){
          return memberRepository.findByNickName(nickName).isPresent();
     }

}
