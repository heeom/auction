package com.ddang.auction.member.repository;

import com.ddang.auction.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

//@Repository
//@Slf4j
//public class JdbcTemplateMemberRepository implements MemberRepository{



    //    private final JdbcTemplate jdbcTemplate;
//
//    //생성자가 유일하면 @Autowired 생략가능하다.
//    public JdbcTemplateMemberRepository(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public Member save(Member member) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("mb_memberId", member.getMemberId());
//        parameters.put("mb_password", member.getPassword());
//        parameters.put("mb_nickname", member.getNickName());
//        parameters.put("mb_email", member.getEmail());
//        parameters.put("mb_regdate", member.getRegTime());
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setId(key.longValue());
//
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findByMemberId(String memberId) {
//        List<Member> result = jdbcTemplate.query("select * from member where mb_memberId = ?", memberRowMapper(), memberId);
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findByNickName(String nickName) {
//        List<Member> result = jdbcTemplate.query("select * from member where mb_nickName=?", memberRowMapper(), nickName);
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return jdbcTemplate.query("select * from member", memberRowMapper());
//    }
//
//    private RowMapper<Member> memberRowMapper(){
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setMemberId(rs.getString("mb_memberId"));
//            member.setPassword(rs.getString("mb_password"));
//            member.setNickName(rs.getString("mb_nickname"));
//            member.setEmail(rs.getString("mb_email"));
//            member.setRegTime(rs.getString("mb_regdate"));
//            return member;
//        };
//    }

//}
