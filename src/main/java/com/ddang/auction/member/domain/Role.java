package com.ddang.auction.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@Builder
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members;
}
