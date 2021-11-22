package com.ddang.auction.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private SecurityUtil() {
    }

    //SecurityContext는 ThreadLocal에 사용자의 정보를 저장
    //SecurityContext에 유저 정보가 저장되는 시점 : 요청이 들어올때 JwtFilter의 doFilter에서 저장
    public static Long getCurrentUsername(){
        final Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("SecurityContext에 인증정보가 없음");
        }
        return Long.parseLong(authentication.getName()); //username Long타입으로 파싱해서 반환
    }
}
