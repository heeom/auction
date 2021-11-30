package com.ddang.auction.member.repository;

import com.ddang.auction.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    //회원ID 조회
    @Query("select m from #{#entityName} m where m.username = ?1")
    Optional<Member> findByUsername(@Param("username") String username);

    @Query("select m from #{#entityName} m where m.nickName = ?1")
    Optional<Member> findByNickName(@Param("nickName") String nickName);

    List<Member> findAll();
}
