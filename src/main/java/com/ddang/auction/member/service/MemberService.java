package com.ddang.auction.member.service;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.Role;
import com.ddang.auction.member.domain.RoleConst;
import com.ddang.auction.member.repository.MemberRepository;
import com.ddang.auction.web.security.SecurityUtil;
import com.ddang.auction.web.security.dto.RefreshToken;
import com.ddang.auction.web.security.dto.TokenDto;
import com.ddang.auction.web.security.repository.RefreshTokenRepository;
import com.ddang.auction.web.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

     private final MemberRepository memberRepository;
     private final RefreshTokenRepository refreshTokenRepository;
     private final PasswordEncoder passwordEncoder;
     private final AuthenticationManagerBuilder authenticationManagerBuilder;
     private final TokenProvider tokenProvider;

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


     public TokenDto login(LoginMember loginMember) {
          //1. id/pw 기반으로 AuthenticationToken생성
          UsernamePasswordAuthenticationToken authenticationToken
                  = new UsernamePasswordAuthenticationToken(loginMember.getUsername(), loginMember.getPassword());

          /*
          2. id/pw검증 : authentication 메서드가 실행될 때, CustomUserDetailsService.loadUserByUsername 실행
          AuthenticationManager : 실제 인증과정이 수행됨
                                 - authenticate(authentication) : UserDetails의 유저정보와 authenticationToken의 유저 정보가 일치하는지 검사한다.
                                 - 인증이 완료된 Authentication 객체에는 username(id)가 들어있다.
                                 */
          Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

          //3. 인증정보를 기반으로 JWT 토큰 생성
          TokenDto token = tokenProvider.createToken(authentication);
          
          //4. RefreshToken저장
          RefreshToken refreshToken = RefreshToken.builder()
                                        .key(authentication.getName())
                                        .value(token.getRefreshToken())
                                        .build();

          refreshTokenRepository.save(refreshToken);

          //5. 생성된 토큰을 발급
          return token;
     }
     
     //SecurityContext에 저장되어 있는 유저 정보 가져오기
     public Member getUserInfo(){
          return memberRepository.findByUsername(String.valueOf(SecurityUtil.getCurrentUsername()))
                  .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));
     }

     public TokenDto reissue(TokenDto token) {
          //1. Refresh Token 검증
          if(!tokenProvider.validateToken(token.getRefreshToken())){
               throw new RuntimeException("유효하지 않은 RefreshToken");
          }

          //2. access token에서 username가져오기
          Authentication authentication = tokenProvider.getAuthentication(token.getAccessToken());

          //3. refresh token 저장소에서 username기반으로 refresh token 조회해오기
          RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                  .orElseThrow(() -> new RuntimeException("로그아웃된 사용자입니다."));

          //4. Refresh token 일치여부 검사
          if(!refreshToken.getValue().equals(token.getRefreshToken())){
               throw new RuntimeException("refresh 토큰의 사용자 정보가 일치하지 않습니다.");
          }
          
          //5. 새로운 Token 생성
          TokenDto newTokenDto = tokenProvider.createToken(authentication);
          
          //6. 토큰 저장소의 refresh token 정보 업데이트
          RefreshToken newRefreshToken = refreshToken.updateValue(newTokenDto.getRefreshToken());
          refreshTokenRepository.save(newRefreshToken);

          return newTokenDto;
     }

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

     private void checkDuplicateUsername(Member member) {
          memberRepository.findByUsername(member.getUsername())
                  .ifPresent(member1 -> {
                       throw new IllegalArgumentException("Duplicate MemberId");
                  });
     }
}
