package com.ddang.auction.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TYPE = "Bearer ";

    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /*
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //1. request header에서 토큰을 꺼냄
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        //2. validateToken으로 유효성 검사
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            //2. 유효한 토큰이면 유저정보 꺼내서 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("SecurityContext에 {} 인증정보 저장, uri : {}", authentication.getName(), requestURI);
        }else{
            log.info("유효한 토큰이 없음, uri : {}", requestURI);
        }

        chain.doFilter(request, response); //요청을 Controller에 넘김 
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //1. request header에서 토큰을 꺼냄
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        //2. validateToken으로 유효성 검사
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            //2. 유효한 토큰이면 유저정보 꺼내서 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("SecurityContext에 {} 인증정보 저장, uri : {}", authentication.getName(), requestURI);
        }else{
            log.info("유효한 토큰이 없음, uri : {}", requestURI);
        }

        filterChain.doFilter(request, response); //요청을 Controller에 넘김

    }

    //Request Header에서 토큰 정보를 꺼내온다
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
