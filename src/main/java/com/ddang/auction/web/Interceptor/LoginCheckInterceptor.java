package com.ddang.auction.web.Interceptor;


import com.ddang.auction.member.domain.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        //비회원 사용자
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("비회원 사용자");
            response.sendRedirect("/members/login?redirectURI="+requestURI);
            return false;
        }
        return true;
    }


}
