//package com.ddang.auction;
//
//import com.ddang.auction.member.repository.JdbcTemplateMemberRepository;
//import com.ddang.auction.member.repository.MemberRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SpringConfig implements WebMvcConfigurer {
//
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public MemberRepository memberRepository(){
//        return new JdbcTemplateMemberRepository(dataSource);
//    }
//
//}
