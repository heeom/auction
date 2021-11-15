package com.ddang.auction.web.config;

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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

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
                .usersByUsernameQuery("select mb_memberId, mb_password, mb_enabled "
                        + "from member "
                        + "where mb_memberId = ?") //authentication : 인증처리
                .authoritiesByUsernameQuery("select email,authority "
                        + "from authorities "
                        + "where email = ?"); //authorization : 인가처리
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
