package com.ddang.auction.member.repository;

import com.ddang.auction.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //저장
    Member save(Member member);

    //sequence로 조회
    Optional<Member> findById(Long id);

    //회원ID 조회
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByNickName(String nickName);

    //회원목록조회
    List<Member> findAll();
}
