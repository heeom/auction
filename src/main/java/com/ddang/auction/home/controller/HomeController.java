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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Stream;


@Slf4j
@RequestMapping("/home")
@Controller
public class HomeController {

    @GetMapping
    public String home(){
        return "redirect:/items";

    }
}
