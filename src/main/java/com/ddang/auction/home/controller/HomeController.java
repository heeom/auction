package com.ddang.auction.home.controller;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.SessionConst;
import com.ddang.auction.web.argumentresolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Slf4j
@RequestMapping("/home")
@Controller
public class HomeController {

//    @GetMapping
    public String home(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) String memberId, Model model){
        return "home/index";
    }

    @GetMapping
    public String homePage(@Login String memberId, Model model){
        return "home/index";
    }

}
