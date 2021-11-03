package com.ddang.auction.web.argumentresolver;

import com.ddang.auction.member.domain.LoginMember;
import com.ddang.auction.member.domain.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class); //@Login 애노테이션이 있는지 확인
        boolean hasLoginMemberType = String.class.isAssignableFrom(parameter.getParameterType());//parameter == String : String 타입인지 (String memberId)
        
        return hasLoginAnnotation && hasLoginMemberType;
    }
    
    //supportsParameter return true인 경우 실행
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null){
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER); //session이 존재하면 memberId String 객체가 리턴됨
    }
}
