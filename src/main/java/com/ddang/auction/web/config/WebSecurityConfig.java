package com.ddang.auction.web.config;

import com.ddang.auction.web.security.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
                .antMatchers("/css/**", "/error", "/js/**", "/*.ico", "/img/**", "/upload/**");
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
//                .and()
//                .formLogin()
//                    .loginPage("/members/login")
//                    .permitAll()
//                .and()
//                .logout()
//                    .permitAll()
                // JwtFilter를 등록한 JwtSecureConfig 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationManagerBuilder객체로 spring 내부에서 인증처리
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder()) //password 암호화
//                .usersByUsernameQuery("select mb_member_id, mb_password, mb_enabled "
//                        + "from member "
//                        + "where mb_member_id = ?") //authentication : 인증처리
//                .authoritiesByUsernameQuery("select m.mb_member_id, r.role_name "
//                        + "from user_role ur inner join member m on ur.user_id = m.id "
//                        + "inner join role r on ur.role_id = r.role_id "
//                        + "where m.mb_member_id = ?"); //authorization : 인가처리
//        //member - role : @ManyToMany + mapping table
//    }



}
