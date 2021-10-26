package com.ddang.auction.member.controller;

import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        memberService.join(member);
        return "home/index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(Member member){
        return "home/index";
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
