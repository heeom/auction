package com.ddang.auction.member.controller;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.SessionConst;
import com.ddang.auction.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String join(Member member, HttpServletRequest request){
        Member joinMember = memberService.join(member);

        if(joinMember == null){
            return "redirect:/join";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, joinMember.getMemberId());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request,
            @RequestParam(defaultValue = "/") String redirectURI){

        HttpSession session = request.getSession();
        session.setAttribute("redirectURI", redirectURI);

        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginMember loginMember, HttpServletRequest request){

        Member member = memberService.login(loginMember);
        if (member == null){
            return "members/loginForm";
        }

        //로그인 세션생성
        //세션이 있으면, 이미 존재하는 세션 반환 / 없으면 신규세션 생성해서 반환
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getMemberId());

        return "redirect:"+session.getAttribute("redirectURI");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false); // session존재하지 않을때 새로운 세션 생성하지 않음
        if (session != null){
            session.invalidate();
        }
        return "redirect:/home";
    }

    @GetMapping("/update")
    public String updateMemberInfoForm(){
        return "members/member";
    }

    @PostMapping("/update")
    public String updateMemberInfo(){
        return "members/member";
    }

    @GetMapping("/member")
    public String getMember(){
        return "";
    }

    public String getMembers(){
        return "";
    }
}
