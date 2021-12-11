package com.ddang.auction.web.security;

import com.ddang.auction.web.security.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *  JWT 토큰의 암호화, 복호화, 검증로직이 모두 TokenProvider에서 이루어진다.
 *  - createToken :
 *      - authentication 객체를 넘겨 받아서 JWT Token을 생성한다.
 *      - authentication.getName() : username(유저아이디)
 *      - Token에 유저, 권한, 시크릿키, 유효기간을 담아 생성한다.
 *
 *  - getAuthentication :
 *      - jwt token을 복호화해서 토큰에 들어있는 정보를 꺼낸다.
 *      - 권한 정보로 UserDetails 객체를 생성해서 UsernamePasswordAuthenticationToken 형태로 리턴
 */

@Slf4j
@Component
public class TokenProvider implements InitializingBean{

    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //7일
    private static final String TOKEN_TYPE = "Bearer ";
    private final String secret;
    private Key key;


    public TokenProvider(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    //secret Base64로 decode -> hmac-Sha 알고리즘 사용해서 SecretKey 생성
    @Override
    public void afterPropertiesSet(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //JWT Token 생성
    public TokenDto createToken(Authentication authentication){
        String authorities = authentication
                .getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenExpireTime = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpireTime = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()                                  // payload
                .setSubject(authentication.getName()) // "sub" : "name"
                .claim(AUTHORITIES_KEY, authorities) //  "auth" : "ROLE_USER"
                .signWith(key, SignatureAlgorithm.HS512)// "alg" : "HS512" signature에 들어갈 secretKey값과 사용할 암호화 알고리즘
                .setExpiration(accessTokenExpireTime) //유효기간 설정
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireTime)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireTime(accessTokenExpireTime.getTime())
                .tokenType(TOKEN_TYPE)
                .build();
    }

    public Authentication getAuthentication(String accessToken){
        //Jwt 파싱해서 body의 claim을 리턴 : claim 생성
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        //claim에서 권한 정보(AUTHORITIES_KEY)를 가져옴
        Collection<? extends GrantedAuthority> authorities = Arrays
                                                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                                                .map(SimpleGrantedAuthority::new)
                                                .collect(Collectors.toList());

        //권한 정보로 UserDetails 객체 생성
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        //user객체, token, 권한 정보를 이용 -> Authentication 객체 리턴
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //토큰 유효성 검증
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("잘못된 JWT 서명");
        } catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰");
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 토큰");
        } catch (IllegalArgumentException e){
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
