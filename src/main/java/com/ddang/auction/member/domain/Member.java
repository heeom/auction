package com.ddang.auction.member.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String userName;
    private String nickName;
    private String zip;
    private String address1;
    private String address2;
    private String phoneNumber;
    private Integer point;
    private Integer ticket;
    private String email;
    private int emailCertify;
    private String emailCertify2;

    private LocalDateTime regTime;
    private LocalDateTime withdrawalTime;
    private LocalDateTime bannedTime;
}
