package com.ddang.auction.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "mb_member_id")
    private String username;

    @Column(name = "mb_password")
    private String password;

    @Column(name = "mb_nickname")
    private String nickName;

    @Column(name = "mb_name")
    private String memberName;

    @Column(name = "mb_email")
    private String email;

    @Column(name = "mb_zipcode")
    private String zip;

    @Column(name = "mb_address1")
    private String address1;

    @Column(name = "mb_address2")
    private String address2;

    @Column(name = "mb_phonenumber")
    private String phoneNumber;

    @Column(name = "mb_regdate")
    private String regTime;

    @Column(name = "mb_enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

}