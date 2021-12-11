package com.ddang.auction.web.config;

import com.ddang.auction.web.security.config.JwtSecurityConfig;
import com.ddang.auction.web.security.controller.JwtAccessDeniedHandler;
import com.ddang.auction.web.security.controller.JwtAuthenticationEntryPoint;
import com.ddang.auction.web.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    public WebSecurityConfig(TokenProvider tokenProvider,
                             JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                             JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }


    /**
     * 스프링 시큐리티 룰을 무시하기 하는 URL 패턴
     */
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/css/**", "/error", "/js/**", "/img/**", "/upload/**", "/*.ico");
    }

    /**
     * 스프링 시큐리티 룰
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                //세션 사용하지 않기때문에 세션 설정 Stateless로
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()//httpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                    .antMatchers("/home","/members/**", "/items").permitAll()
                    .anyRequest().authenticated()

                .and()
                .logout()
                    .permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)); //JwtFilter를 등록한 JwtSecureConfig 적용
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
