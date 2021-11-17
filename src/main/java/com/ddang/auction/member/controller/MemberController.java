package com.ddang.auction.member.controller;

import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.domain.SessionConst;
import com.ddang.auction.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        session.setAttribute(SessionConst.LOGIN_MEMBER, joinMember.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request,
            @RequestParam(defaultValue = "/home") String redirectURI){

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.REDIRECT_URI, redirectURI);

        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(Member member, HttpServletRequest request){

        log.info("Controller.login.post : {}", member.getUsername());

//        Member loginMember = memberService.login(member);
//        if (member == null){
//            return "members/loginForm";
//        }

        HttpSession session = request.getSession(false);

//        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getMemberId());

//        return "redirect:"+session.getAttribute(SessionConst.REDIRECT_URI);
        return "home/index";
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
