package com.ddang.auction.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/home", "/members/join", "/members/duplicate/**",  "/members/logout", "/css/**", "/error", "/js/**", "/*.ico", "/img/**", "/items").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/members/login")
                    .permitAll()
                .and()
                .logout()
                    .permitAll();
    }

    //AuthenticationManagerBuilder객체로 spring 내부에서 인증처리
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder()) //password 암호화
                .usersByUsernameQuery("select mb_member_id, mb_password, mb_enabled "
                        + "from member "
                        + "where mb_member_id = ?") //authentication : 인증처리
                .authoritiesByUsernameQuery("select m.mb_member_id, r.role_name "
                        + "from user_role ur inner join member m on ur.user_id = m.id "
                        + "inner join role r on ur.role_id = r.role_id "
                        + "where m.mb_member_id = ?"); //authorization : 인가처리
        //member - role : @ManyToMany + mapping table
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user = User.withUserDetails()
                .username("memberId")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    */
}
