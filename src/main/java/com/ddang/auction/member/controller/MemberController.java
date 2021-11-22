package com.ddang.auction.member.controller;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.SessionConst;
import com.ddang.auction.member.service.MemberService;
import com.ddang.auction.web.security.JwtFilter;
import com.ddang.auction.web.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public MemberController(MemberService memberService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
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
    public String login(LoginMember loginMember, HttpServletRequest request, HttpServletResponse response){
        String token = memberService.login(loginMember);
        ResponseEntity.ok(token);
        log.info("token : {}", token);
        return "redirect:/home";
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
