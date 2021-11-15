package com.ddang.auction.member.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString
@Entity
@Table(name = "member")
public class Member {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 Auto_Increment 방식을 사용
    private Long id;

    private String memberId;

    private String password;

    private String nickName;

    private String memberName;

    private String email;

    private String zip;
    private String address1;
    private String address2;
    private String phoneNumber;
    private String regTime;
}