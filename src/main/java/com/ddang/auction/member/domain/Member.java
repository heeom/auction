package com.ddang.auction.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String nickName;
    private String email;
    private String zip;
    private String memberName;
    private String address1;
    private String address2;
    private String phoneNumber;
    private String regTime;

}