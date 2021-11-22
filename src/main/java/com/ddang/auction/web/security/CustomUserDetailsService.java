package com.ddang.auction.web.security;

import com.ddang.auction.member.domain.Member;
import com.ddang.auction.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * username으로 DB에서 사용자 정보 조회 -> UserDetails객체 생성해서 리턴
     * - userDetails와 Authentication의 password(credential) 비교해서 검증한다
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(user -> createUser(user.getUsername(), user))
                .orElseThrow(() -> new UsernameNotFoundException(username + "를 DB에서 조회할 수 없습니다."));
    }

    //DB에 사용자 존재하면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUser(String username, Member user){
        if(!user.isEnabled()){
            throw new RuntimeException(username + " Disable");
        }

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                                                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                                                    .collect(Collectors.toList());

        log.info("user Authorities : {}", grantedAuthorities.get(0).getAuthority().toString());

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities); //member_id, password,
    }

}
