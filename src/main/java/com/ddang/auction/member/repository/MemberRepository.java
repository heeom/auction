package com.ddang.auction.member.repository;

import com.ddang.auction.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
//    @Query("select m from #{#entityName} m where m.username = ?1")
    Optional<Member> findByUsername(String username);

//    @Query("select m from #{#entityName} m where m.nickName = ?1")
    Optional<Member> findByNickName(String nickName);
}
