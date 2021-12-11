package com.ddang.auction.member.controller;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.service.MemberService;
import com.ddang.auction.web.security.JwtFilter;
import com.ddang.auction.web.security.TokenProvider;
import com.ddang.auction.web.security.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

import static com.ddang.auction.web.security.JwtFilter.AUTHORIZATION_HEADER;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinForm(){
        return "members/joinForm";
    }

    @PostMapping("/join")
    public String join(Member member){
        Member joinMember = memberService.join(member);
        if(joinMember == null){
            return "redirect:/join";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginMember loginMember, HttpServletResponse response){
        TokenDto token = memberService.login(loginMember);
        response.setHeader("accessToken", token.getTokenType()+token.getAccessToken());
        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        response.addCookie(cookie);

        return "home/index";
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(HttpServletRequest request, HttpServletResponse response){
        Cookie refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst().get();

        TokenDto tokenDto = TokenDto.builder()
                .accessToken(request.getHeader(AUTHORIZATION_HEADER).substring(7))
                .refreshToken(refreshToken.getValue())
                .build();

        TokenDto token = memberService.reissue(tokenDto);

        response.setHeader("accessToken", JwtFilter.BEARER_TYPE+token.getAccessToken());
        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        response.addCookie(cookie);

        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/duplicate/username/{username}")
    public ResponseEntity<Boolean> checkDuplicateUsername(@PathVariable String username){
        return ResponseEntity.ok(memberService.checkUsernameExist(username));
    }

    @GetMapping("/duplicate/nickName/{nickName}")
    public ResponseEntity<Boolean> checkDuplicateNickName(@PathVariable String nickName){
        return ResponseEntity.ok(memberService.checkNickNameExist(nickName));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false); // session존재하지 않을때 새로운 세션 생성하지 않음
        if (session != null){
            session.invalidate();
        }
        return "redirect:/home";
    }


}
