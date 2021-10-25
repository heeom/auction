package com.ddang.auction.member.controller;

import com.ddang.auction.member.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
